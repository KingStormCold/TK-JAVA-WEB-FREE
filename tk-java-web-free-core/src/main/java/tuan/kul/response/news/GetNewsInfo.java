package tuan.kul.response.news;

import java.util.List;

import tuan.kul.dto.NewDto;
import tuan.kul.response.category.CategoryInfo;

public class GetNewsInfo {
    
    private String id;
    
    private String title;
    
    private String content;

    private String description;
    
    private String image;
    
    private String category;
    
    private List<CategoryInfo> listCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<CategoryInfo> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<CategoryInfo> listCategory) {
        this.listCategory = listCategory;
    }

    public GetNewsInfo() {
    }
    
    public GetNewsInfo(NewDto dto, List<CategoryInfo> infos) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.description = dto.getDescription();
        this.image = dto.getImage();
        this.category = dto.getCategory().getCode();
        this.listCategory = infos;
    }
    
    public GetNewsInfo(List<CategoryInfo> infos) {
        this.listCategory = infos;
    }
}
