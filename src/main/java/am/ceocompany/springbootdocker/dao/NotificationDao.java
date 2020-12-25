package am.ceocompany.springbootdocker.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class NotificationDao {

    private Long notificationId;

    private LocalDateTime time;

    private Boolean delivered;

    CommentDao comment;
}
