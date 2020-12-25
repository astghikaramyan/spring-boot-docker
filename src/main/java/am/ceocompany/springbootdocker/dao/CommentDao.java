package am.ceocompany.springbootdocker.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class CommentDao {

    private Long commentId;

    @NotNull(message = "Field of comment cannot be missing or empty.")
    private String comment;

    private LocalDateTime time;

    NotificationDao notification;
}
