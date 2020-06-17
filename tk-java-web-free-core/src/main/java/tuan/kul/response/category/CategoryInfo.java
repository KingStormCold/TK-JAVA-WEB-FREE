package tuan.kul.response.category;

import org.apache.commons.lang.StringUtils;

import tuan.kul.common.DateUtils;
import tuan.kul.dto.CategoryDto;

public class CategoryInfo {
    
    private String categoryCode;
    
    private String createdBy;
    
    private String createdDate;
    
    private String categoryName;
    
    private String categoryFatherCode;
    
    private String categoryFatherName;
    
    private String updatedDate;
    
    private String updatedBy;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCategoryFatherCode() {
        return categoryFatherCode;
    }

    public void setCategoryFatherCode(String categoryFatherCode) {
        this.categoryFatherCode = categoryFatherCode;
    }

    public String getCategoryFatherName() {
        return categoryFatherName;
    }

    public void setCategoryFatherName(String categoryFatherName) {
        this.categoryFatherName = categoryFatherName;
    }

    public CategoryInfo() {

    }
    
    public CategoryInfo(CategoryDto dto, DateUtils dateUtils) {
       this.categoryCode = dto.getCode();
       this.categoryName = dto.getCategory();
       this.categoryFatherCode = !StringUtils.isEmpty(dto.getCategoryFatherCode()) ? dto.getCategoryFatherCode() : ""; 
       this.categoryFatherName = !StringUtils.isEmpty(dto.getCategoryFather()) ? dto.getCategoryFather() : "";
       this.createdBy = !StringUtils.isEmpty(dto.getCreatedBy()) ? dto.getCreatedBy() : "";
       this.updatedBy = !StringUtils.isEmpty(dto.getModifiedBy()) ? dto.getModifiedBy() : "";
       this.createdDate = dateUtils.convertDateToString(DateUtils.FORMAT_YYYY_MM_DD_HHMMSS, dto.getCreatedDate());
       this.updatedDate = dateUtils.convertDateToString(DateUtils.FORMAT_YYYY_MM_DD_HHMMSS, dto.getModifiedDate());
    }
}
