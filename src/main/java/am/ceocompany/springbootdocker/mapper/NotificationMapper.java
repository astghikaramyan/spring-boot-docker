package am.ceocompany.springbootdocker.mapper;

import am.ceocompany.springbootdocker.dao.NotificationDao;
import am.ceocompany.springbootdocker.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public interface NotificationMapper extends Mapper<NotificationEntity, NotificationDao> {
}
