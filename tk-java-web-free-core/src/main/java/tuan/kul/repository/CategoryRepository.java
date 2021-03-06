package tuan.kul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tuan.kul.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String>, CategoryRepositoryCustom{

	List<CategoryEntity> findAllByCategoryFatherCode(String categoryFatherCode);
}
