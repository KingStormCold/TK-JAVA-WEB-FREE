package tuan.kul.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tuan.kul.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, String>{

	@Query(value = "SELECT r FROM RoleEntity r ORDER BY r.roleId")
	Page<RoleEntity> findAllByPaging (Pageable pageable);
}
