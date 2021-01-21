package tuan.kul.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tuan.kul.common.DateUtils;
import tuan.kul.converter.CategoryConverter;
import tuan.kul.dto.CategoryDto;
import tuan.kul.entity.CategoryEntity;
import tuan.kul.entity.RoleEntity;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.repository.CategoryRepository;
import tuan.kul.request.category.CreateCategory;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.Pagination;
import tuan.kul.response.ResultResponse;
import tuan.kul.response.category.CategoryFatherList;
import tuan.kul.response.category.CategoryInfo;
import tuan.kul.response.category.CategoryResponse;
import tuan.kul.response.category.ListCategoryInfo;
import tuan.kul.response.role.ListRoleInfo;
import tuan.kul.response.role.RoleInfo;

@Service
@Transactional
public class CategoryService {

    private static final Logger logger = Logger.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    public ObjectInfoResponse<ListCategoryInfo> findAll(String pageNum, String pageSize) {
    	Integer page = Integer.valueOf(pageNum);
		Integer size = Integer.valueOf(pageSize);
		Sort sort = new Sort(Direction.DESC, "createdDate");
		Pageable pageable = new PageRequest(page - 1, size, sort);
		Page<CategoryEntity> pageNew = categoryRepository.findAll(pageable);
		List<CategoryDto> categoryDtos = pageNew.getContent().stream()
				.map(entity -> CategoryDto.of(entity)).collect(Collectors.toList());
		Pagination pagination = new Pagination(page, size, pageNew.getTotalPages(), pageNew.getTotalElements());
		
		List<CategoryInfo> categoryInfoList = categoryDtos.stream().map(dto -> new CategoryInfo(dto)).collect(Collectors.toList());
		
		ListCategoryInfo result = new ListCategoryInfo(categoryInfoList, pagination, categoryInfoList.isEmpty());
		
		return new ObjectInfoResponse<>(HttpStatusCode._200.getCode(), HttpStatusCode._200.getText(),
				result);
    }
    
    public List<CategoryInfo> getAllRootCategory() {
        List<CategoryEntity> categoryEntities = categoryRepository.getAllCategory();
        List<CategoryInfo> result = new ArrayList<>();
        for(CategoryEntity categoryEntity : categoryEntities) {
        	if (org.springframework.util.StringUtils.isEmpty(categoryEntity.getCategoryFatherCode())) {
        		result.add(new CategoryInfo(categoryConverter.convertToDto(categoryEntity)));
        	}
        }
        return result;
    }

    public ResultResponse insertCategory(CreateCategory request, String userRequest) {
        CategoryDto categoryDto;
        CategoryDto rootCategory = findOne(request.getRootCatetegory());
        if (!StringUtils.isEmpty(request.getRootCatetegory()) && rootCategory == null) {
            return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_FOUND.getText());
        }
        if (!StringUtils.isEmpty(request.getCode())) {
            //update category
            categoryDto = findOne(request.getCode());
            if (categoryDto == null) {
                return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_FOUND.getText());
            }
            categoryDto.setCategory(request.getCategoryName());
            categoryDto.setModifiedDate(new Date(System.currentTimeMillis()));
            categoryDto.setModifiedBy(userRequest);
        } else {
            //insert category
            categoryDto = findOne(convertNameToCode(request.getCategoryName()));
            if (categoryDto != null) {
                return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_EXIST_CATEGORY.getText());
            }
            categoryDto = new CategoryDto();
            categoryDto.setCode(convertNameToCode(request.getCategoryName()));
            categoryDto.setCategory(request.getCategoryName());
            categoryDto.setCreatedBy(userRequest);
            categoryDto.setCreatedDate(new Date(System.currentTimeMillis()));
        }
        categoryDto.setCategoryFatherCode("");
        categoryDto.setCategoryFather("");
        if (rootCategory != null) {
            long isCategoryFather = checkCategoryFather(categoryDto.getCode());
            if (isCategoryFather != 0) {
                return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_CATEGORY_IS_FATHER.getText());
            }
            categoryDto.setCategoryFatherCode(rootCategory.getCode());
            categoryDto.setCategoryFather(rootCategory.getCategory());
        }
        try {
            categoryRepository.save(categoryConverter.convertToEntity(categoryDto));
            return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
        } catch (RollbackException e) {
            logger.info(e.toString());
            return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.FAIL.getText());
        }
    }
    
    public ResultResponse deleteCategory(final String code) {
        CategoryDto deleteCategory = findOne(code);
        if (deleteCategory == null) {
            return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_FOUND.getText());
        }
        deleteCategory(deleteCategory);
        return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
    }

    public CategoryDto findOne(final String code) {
        return categoryConverter.convertToDto(categoryRepository.findOne(code));
    }
    
    public void deleteCategory(final CategoryDto deleteCategory) {
        categoryRepository.delete(categoryConverter.convertToEntity(deleteCategory));
    }

    private String convertNameToCode(final String categoryName) {
        String removeVietnamese = Normalizer.normalize(categoryName, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(removeVietnamese).replaceAll("");
        result = result.trim().toUpperCase().replaceAll("\\s", "").replaceAll("Đ", "D");
        return result;
    }
    
    public long checkCategoryFather(final String categoryFather) {
        return categoryRepository.countCategoryFather(categoryFather);
    }
}
