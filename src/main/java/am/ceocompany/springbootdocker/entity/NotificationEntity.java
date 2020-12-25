package am.ceocompany.springbootdocker.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "notification")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class NotificationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    private LocalDateTime time;

    private Boolean delivered;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", nullable = false)
    CommentEntity comment;

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "notificationId=" + notificationId +
                ", time=" + time +
                ", delivered=" + delivered +
                '}';
    }
}
