package tuan.kul.dto;

import java.util.List;

/**
 * 
 * @author tuan.thai
 *
 */

public class NewDto extends BaseDto {
    private String id;
    private String title;
    private String content;
    private String description;
    private int view;
    private boolean top;
    private String image;
    private String code;
    private CategoryDto category;
    private List<CommentDto> commentEntities;

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

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<CommentDto> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(List<CommentDto> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
