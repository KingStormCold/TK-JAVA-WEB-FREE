package tuan.kul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tuan.kul.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String>, CategoryRepositoryCustom{

}
