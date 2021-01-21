package tuan.kul.dto;

import java.util.List;

import tuan.kul.entity.CategoryEntity;

/**
 * 
 * @author tuan.thai
 *
 */
public class CategoryDto extends BaseDto {
    private String code;
    private String category;
    private String categoryFather;
    private String categoryFatherCode;
    private List<NewDto> newEntities;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<NewDto> getNewEntities() {
        return newEntities;
    }

    public void setNewEntities(List<NewDto> newEntities) {
        this.newEntities = newEntities;
    }

    public String getCategoryFather() {
        return categoryFather;
    }

    public void setCategoryFather(String categoryFather) {
        this.categoryFather = categoryFather;
    }

    public String getCategoryFatherCode() {
        return categoryFatherCode;
    }

    public void setCategoryFatherCode(String categoryFatherCode) {
        this.categoryFatherCode = categoryFatherCode;
    }

    public CategoryDto() {

    }
    
    public static CategoryDto of(CategoryEntity entity) {
    	CategoryDto result = new CategoryDto();
    	result.setCode(entity.getCode());
    	result.setCategory(entity.getCategory());
    	result.setCategoryFatherCode(entity.getCategoryFatherCode());
    	result.setCategoryFather(entity.getCategoryFather());
    	result.setCreatedBy(entity.getCreatedBy());
    	result.setCreatedDate(entity.getCreatedDate());
    	result.setModifiedBy(entity.getModifiedBy());
    	result.setModifiedDate(entity.getModifiedDate());
    	return result;
    }
}
