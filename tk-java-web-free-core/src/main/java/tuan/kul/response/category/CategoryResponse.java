package tuan.kul.response.category;

import java.util.List;

import tuan.kul.response.ResultResponse;

public class CategoryResponse extends ResultResponse{
    
    private List<CategoryInfo> categoryInfos;
    private List<CategoryFatherList> categoryFatherList;
    private String code;

    public List<CategoryInfo> getCategoryInfos() {
        return categoryInfos;
    }

    public void setCategoryInfos(List<CategoryInfo> categoryInfos) {
        this.categoryInfos = categoryInfos;
    }

    public List<CategoryFatherList> getCategoryFatherList() {
        return categoryFatherList;
    }

    public void setCategoryFatherList(List<CategoryFatherList> categoryFatherList) {
        this.categoryFatherList = categoryFatherList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CategoryResponse(String result, String message, List<CategoryInfo> categoryInfos, List<CategoryFatherList> categoryFatherList) {
        super(result, message);
        this.categoryInfos = categoryInfos;
        this.categoryFatherList = categoryFatherList;
    }
    
    public CategoryResponse(String result, String message) {
        super(result, message);
    }
}
