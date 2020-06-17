package tuan.kul.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

    @Id
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "category_father")
    private String categoryFather;

    @Column(name = "category_father_code")
    private String categoryFatherCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private List<NewEntity> newEntities;

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

    public String getCategoryFather() {
        return categoryFather;
    }

    public void setCategoryFather(String categoryFather) {
        this.categoryFather = categoryFather;
    }

    public List<NewEntity> getNewEntities() {
        return newEntities;
    }

    public void setNewEntities(List<NewEntity> newEntities) {
        this.newEntities = newEntities;
    }

    public String getCategoryFatherCode() {
        return categoryFatherCode;
    }

    public void setCategoryFatherCode(String categoryFatherCode) {
        this.categoryFatherCode = categoryFatherCode;
    }

    public CategoryEntity() {

    }
}
