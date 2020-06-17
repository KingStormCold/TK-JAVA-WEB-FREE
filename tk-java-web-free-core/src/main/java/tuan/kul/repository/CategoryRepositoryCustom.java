package tuan.kul.repository;

import java.util.List;

import tuan.kul.entity.CategoryEntity;

public interface CategoryRepositoryCustom {
    List<CategoryEntity> getAllCategory();
    long countCategoryFather(String categoryFatherCode);
}
