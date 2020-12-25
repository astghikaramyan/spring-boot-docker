package am.ceocompany.springbootdocker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "comment")
@Data
@EqualsAndHashCode
@Getter
@Setter
//@NoArgsConstructor
public class CommentEntity implements Serializable {

    public CommentEntity(String comment) {
        this.comment = comment;
    }

    public CommentEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private String comment;

    private LocalDateTime time;


    @OneToOne(mappedBy = "comment")
    NotificationEntity notification;

    @Override
    public String toString() {
        return "CommentEntity{" +
                "commentId=" + commentId +
                ", comment='" + comment + '\'' +
                ", time=" + time +
                '}';
    }
}
