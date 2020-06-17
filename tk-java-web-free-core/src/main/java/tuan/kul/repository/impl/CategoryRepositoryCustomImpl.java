package tuan.kul.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import tuan.kul.common.Constant;
import tuan.kul.entity.CategoryEntity;
import tuan.kul.repository.CategoryRepositoryCustom;

@Repository
@EnableJpaRepositories(
        basePackages = "tuan.kul.repository", repositoryImplementationPostfix = "CustomImpl")
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;
    
    private Criteria criteriaCategory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<CategoryEntity> getAllCategory() {
        criteriaCategory = entityManager.unwrap(Session.class).createCriteria(CategoryEntity.class);
        criteriaCategory.addOrder(Order.desc(Constant.JPA_CREATED_DATE));
        return criteriaCategory.list();
    }

    @Override
    public long countCategoryFather(String categoryFatherCode) {
        criteriaCategory = entityManager.unwrap(Session.class).createCriteria(CategoryEntity.class);
        criteriaCategory.add(Restrictions.eq(Constant.JPA_CATEGORY_FATHER_CODE, categoryFatherCode));
        long result = (long) criteriaCategory.setProjection(Projections.rowCount()).uniqueResult();
        return result;
    }

}
