package tuan.kul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tuan.kul.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {

}
