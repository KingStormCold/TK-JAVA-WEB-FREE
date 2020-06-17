package tuan.kul.response.category;

import tuan.kul.dto.CategoryDto;

public class CategoryFatherList {
    
    private String code;
    
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryFatherList() {

    }

    public CategoryFatherList(CategoryDto dto) {
        this.code = dto.getCode();
        this.name = dto.getCategory();
    }
}
