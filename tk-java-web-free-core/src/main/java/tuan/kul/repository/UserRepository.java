package tuan.kul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tuan.kul.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO user_role VALUES (:roleId, :userName)", nativeQuery = true)
	public void insertUserRole(@Param("roleId")String roleId, @Param("userName")String userName);

	@Query(value = "SELECT COUNT(role_id) FROM user_role WHERE role_id = :roleId AND user_name = :userName", nativeQuery = true)
	public int countUserRole(@Param("roleId")String roleId, @Param("userName")String userName);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM user_role WHERE role_id = :roleId AND user_name = :userName", nativeQuery = true)
	public void deleteUserRole(@Param("roleId")String roleId, @Param("userName")String userName);
	
	public UserEntity findByUserNameAndOnline(String userName, boolean online);
}
