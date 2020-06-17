package tuan.kul.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "new")
public class NewEntity extends BaseEntity {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "TEXT", name = "content", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT", name = "description", nullable = false)
    private String description;

    @Column(name = "view")
    private int view;

    @Column(name = "top")
    private boolean top;

    @Column(name = "image")
    private String image;
    
    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "code", insertable = false, updatable = false, nullable = false)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "newEntity")
    private List<CommentEntity> commentEntities;

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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
