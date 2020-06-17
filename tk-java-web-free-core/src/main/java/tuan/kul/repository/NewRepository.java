package tuan.kul.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tuan.kul.entity.NewEntity;

@Repository
public interface NewRepository extends JpaRepository<NewEntity, String> {

	@Query(value = "SELECT n FROM NewEntity n WHERE n.title LIKE %:title% AND DATE(n.createdDate) BETWEEN DATE(:startDate) AND DATE(:endDate)")
	Page<NewEntity> findByTitleAndBetweenDate(@Param("title") String title, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query(value = "SELECT n FROM NewEntity n WHERE n.createdBy LIKE %:createdBy% AND DATE(n.createdDate) BETWEEN DATE(:startDate) AND DATE(:endDate)")
	Page<NewEntity> findByCreatedByAndBetweenDate(@Param("createdBy") String createdBy, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

	@Query(value = "SELECT n FROM NewEntity n WHERE n.modifiedBy LIKE %:modifiedBy% AND DATE(n.createdDate) BETWEEN DATE(:startDate) AND DATE(:endDate)")
	Page<NewEntity> findByModifiedByAndBetweenDate(@Param("modifiedBy") String modifiedBy, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

	@Query(value = "SELECT n FROM NewEntity n ORDER BY n.createdDate DESC")
	Page<NewEntity> getLastNewDetails(Pageable pageable);
}
