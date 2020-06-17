package tuan.kul.dto;

/**
 * 
 * @author tuan.thai
 *
 */
public class CommentDto extends BaseDto {
    private String id;
    private String content;
    private String parent;
    private NewDto newEntity;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getParent() {
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }
    public NewDto getNewEntity() {
        return newEntity;
    }
    public void setNewEntity(NewDto newEntity) {
        this.newEntity = newEntity;
    }
}
