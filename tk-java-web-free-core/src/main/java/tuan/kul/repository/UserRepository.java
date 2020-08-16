package tuan.kul.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tuan.kul.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	@Modifying
	@Query(value = "INSERT INTO tk_java_web_free.user_role VALUES (:roleId, :userName)", nativeQuery = true)
	public void insertUserRole(@Param("roleId")String roleId, @Param("userName")String userName);

	@Query(value = "SELECT COUNT (role_id) FROM tk_java_web_free.user_role WHERE role_id = :roleId AND user_name = :userName", nativeQuery = true)
	public int countUserRole(@Param("roleId")String roleId, @Param("userName")String userName);
	
	@Modifying
	@Query(value = "DELETE FROM tk_java_web_free.user_role WHERE role_id = :roleId AND user_name = :userName", nativeQuery = true)
	public void deleteUserRole(@Param("roleId")String roleId, @Param("userName")String userName);
	
	public UserEntity findByUserNameAndOnline(String userName, boolean online);
	
	public Page<UserEntity> findAllByOnline(Pageable pageable ,boolean online);
	
}
