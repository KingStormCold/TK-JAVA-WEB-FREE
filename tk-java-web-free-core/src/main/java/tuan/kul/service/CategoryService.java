package tuan.kul.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuan.kul.common.DateUtils;
import tuan.kul.converter.CategoryConverter;
import tuan.kul.dto.CategoryDto;
import tuan.kul.entity.CategoryEntity;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.repository.CategoryRepository;
import tuan.kul.request.category.CreateCategory;
import tuan.kul.response.ResultResponse;
import tuan.kul.response.category.CategoryFatherList;
import tuan.kul.response.category.CategoryInfo;
import tuan.kul.response.category.CategoryResponse;

@Service
@Transactional
public class CategoryService {

    private static final Logger logger = Logger.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    public CategoryResponse findAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.getAllCategory();
        List<CategoryInfo> categoryInfos = new ArrayList<>();
        List<CategoryFatherList> categoryFatherLists = new ArrayList<>();
        for(CategoryEntity categoryEntity : categoryEntities) {
            if (StringUtils.isBlank(categoryEntity.getCategoryFatherCode())) {
                categoryFatherLists.add(new CategoryFatherList(categoryConverter.convertToDto(categoryEntity)));
            }
            categoryInfos.add(new CategoryInfo(categoryConverter.convertToDto(categoryEntity)));
        }
        return new CategoryResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText(), categoryInfos, categoryFatherLists);
    }
    
    public List<CategoryInfo> getAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.getAllCategory();
        List<CategoryInfo> result = new ArrayList<>();
        for(CategoryEntity categoryEntity : categoryEntities) {
            result.add(new CategoryInfo(categoryConverter.convertToDto(categoryEntity)));
        }
        return result;
    }

    public ResultResponse insertCategory(CreateCategory request, String userRequest) {
        CategoryDto categoryDto;
        CategoryDto categoryFather = findOne(request.getCategoryFatherCode());
        if (!StringUtils.isEmpty(request.getCategoryFatherCode()) && categoryFather == null) {
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
        if (categoryFather != null) {
            long isCategoryFather = checkCategoryFather(categoryDto.getCode());
            if (isCategoryFather != 0) {
                return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_CATEGORY_IS_FATHER.getText());
            }
            categoryDto.setCategoryFatherCode(categoryFather.getCode());
            categoryDto.setCategoryFather(categoryFather.getCategory());
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
