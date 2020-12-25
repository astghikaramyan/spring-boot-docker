package am.ceocompany.springbootdocker.repository;

import am.ceocompany.springbootdocker.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
