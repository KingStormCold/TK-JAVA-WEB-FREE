package tuan.kul.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tuan.kul.common.Constant;
import tuan.kul.common.DateUtils;
import tuan.kul.converter.NewConverter;
import tuan.kul.dto.CategoryDto;
import tuan.kul.dto.NewDto;
import tuan.kul.entity.NewEntity;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.repository.NewRepository;
import tuan.kul.request.news.InsertUpdateNewRequest;
import tuan.kul.request.news.SearchRequest;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.Pagination;
import tuan.kul.response.ResultResponse;
import tuan.kul.response.category.CategoryInfo;
import tuan.kul.response.news.GetNewsInfo;
import tuan.kul.response.news.ListNewInfo;
import tuan.kul.response.news.NewsInfo;

@Service
@Transactional
public class NewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private NewConverter newConverter;

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private FileService fileService;

    public ListNewInfo searchNews(SearchRequest request, String userName) {
        Date startDate = DateUtils.convertStringToDate(request.getStartDate());
        Date endDate = DateUtils.convertStringToDate(request.getEndDate());
        int pageNum = (Integer.valueOf(request.getPageNum()) != 0) ? Integer.valueOf(request.getPageNum()) - 1
                : Integer.valueOf(request.getPageNum());
        int pageSize = (Integer.valueOf(request.getPageSize()) != 0) ? Integer.valueOf(request.getPageSize()) : 10;
        Direction direction = Direction.valueOf("DESC");
        List<String> properties = Arrays.asList(Constant.JPA_CREATED_DATE);
        Sort sort = new Sort(direction, properties);
        PageRequest pageable = new PageRequest(pageNum, pageSize, sort);
        Page<NewEntity> pageNew = new PageImpl<>(new ArrayList<NewEntity>());
        switch (request.getSearchBy()) {
        case Constant.JPA_NEW_TITLE:
            pageNew = newRepository.findByTitleAndBetweenDate(request.getSearchValue(), startDate, endDate, pageable);
            break;
        case Constant.JPA_NEW_CREATED_BY:
            pageNew = newRepository.findByCreatedByAndBetweenDate(request.getSearchValue(), startDate, endDate,
                    pageable);
            break;
        case Constant.JPA_NEW_MODIFIED_BY:
            pageNew = newRepository.findByModifiedByAndBetweenDate(request.getSearchValue(), startDate, endDate,
                    pageable);
            break;
        default:
            pageNew = newRepository.getLastNewDetails(pageable);
            break;
        }
        List<NewsInfo> result = new ArrayList<>();
        for (NewEntity newEntity : pageNew) {
            NewDto newDto = newConverter.convertToDto(newEntity);
            result.add(new NewsInfo(newDto.getId(), newDto.getCreatedBy(),
                    DateUtils.convertDateToString(DateUtils.FORMAT_YYYY_MM_DD_HHMMSS, newDto.getCreatedDate()),
                    newDto.getTitle(),
                    DateUtils.convertDateToString(DateUtils.FORMAT_YYYY_MM_DD_HHMMSS, newDto.getModifiedDate()),
                    newDto.getModifiedBy()));
        }
        Pagination pagination = new Pagination(pageNum + 1, pageSize, pageNew.getTotalPages(),
                pageNew.getTotalElements());
        return new ListNewInfo(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText(), result, pagination,
                result.isEmpty());
    }

    public ObjectInfoResponse<GetNewsInfo> getNewInfo(final String id) {
        List<CategoryInfo> categoryDtos = categoryService.getAll();
        if (StringUtils.isEmpty(id)) {
            return new ObjectInfoResponse<GetNewsInfo>(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText(),
                    new GetNewsInfo(categoryDtos));
        }
        NewDto newDto = newConverter.convertToDto(newRepository.findOne(id));
        if (StringUtils.isEmpty(newDto)) {
            return new ObjectInfoResponse<GetNewsInfo>(HttpStatusCode._200.getCode(),
                    ErrorCodeEnum.ERROR_NOT_FOUND.getText(), new GetNewsInfo());
        }
        return new ObjectInfoResponse<GetNewsInfo>(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText(),
                new GetNewsInfo(newDto, categoryDtos));
    }

    public ResultResponse insertOrUpdateNew(final InsertUpdateNewRequest request) throws IOException, URISyntaxException {
        CategoryDto categoryDto = categoryService.findOne(request.getNewCategory());
        if (StringUtils.isEmpty(categoryDto)) {
            return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_EXIST_CATEGORY.getText());
        }
        NewDto newDto;
        if (StringUtils.isEmpty(request.getNewId())) {
//            String imageName = fileService.uploadFile(request.getNewFile());
//            if (StringUtils.isEmpty(imageName)) {
//                return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_FILE.getText());
//            }
            newDto = new NewDto();
            newDto.setId(UUID.randomUUID().toString());
            newDto.setCode(categoryDto.getCode());
            newDto.setTitle(request.getNewTitle());
            newDto.setDescription(request.getNewDescription());
//            newDto.setImage(imageName);
            newDto.setContent(request.getNewContent());
            newDto.setTop(StringUtils.isEmpty(request.getNewTop()));
            newDto.setView(0);
            newDto.setCreatedDate(new Date());
            newDto.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            save(newDto);
            return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
        } else {
            newDto = findById(request.getNewId());
            if (StringUtils.isEmpty(newDto)) {
                return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_EXIST_NEWS.getText());
            }
        }
        return new ResultResponse(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
    }

    public NewDto findById(final String newId) {
        return newConverter.convertToDto(newRepository.findOne(newId));
    }

    public boolean save(NewDto dto) {
        try {
            newRepository.save(newConverter.convertToEntity(dto));
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
