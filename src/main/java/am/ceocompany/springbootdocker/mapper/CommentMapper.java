package am.ceocompany.springbootdocker.mapper;

import am.ceocompany.springbootdocker.dao.CommentDao;
import am.ceocompany.springbootdocker.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public interface CommentMapper extends Mapper<CommentEntity, CommentDao> {
}
