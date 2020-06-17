package tuan.kul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tuan.kul.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{

}
