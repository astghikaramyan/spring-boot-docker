package am.ceocompany.springbootdocker.service;


import am.ceocompany.springbootdocker.entity.NotificationEntity;

import java.util.List;

public interface NotificationService {
    NotificationEntity getById(long id);

    NotificationEntity save(NotificationEntity notificationEntity);

    void delete(long id);

    List<NotificationEntity> get(int page, int perPage);

    NotificationEntity updateNotification(NotificationEntity notificationEntity);
}
