package am.ceocompany.springbootdocker.service.serviceimpl;

import am.ceocompany.springbootdocker.entity.CommentEntity;
import am.ceocompany.springbootdocker.entity.NotificationEntity;
import am.ceocompany.springbootdocker.service.CommentService;
import am.ceocompany.springbootdocker.service.NotificationService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Transactional
@Aspect
public class BusinessLogic {

    @Autowired
    private CommentService commentService;

    @Autowired
    private NotificationService notificationService;

    private static Logger logger = LoggerFactory.getLogger(BusinessLogic.class);

    public static void sleepAndRandomThrowRuntimeException(int seconds, int
            exceptionProbabilityProc) {
        try {
            Thread.sleep((long) (seconds * 1000 * Math.random()));
        } catch (InterruptedException e) {

        }
        int randomProc = (int) (100 * Math.random());
        if (exceptionProbabilityProc > randomProc) throw new RuntimeException();
    }

    @AfterReturning(
            pointcut = "execution(* am.ceocompany.springbootdocker.service.CommentService.save(..))",
            returning = "retVal"
    )
    public void doSomeWorkOnCommentCreation(CommentEntity retVal) {
        if (retVal != null) {
            try {
                sleepAndRandomThrowRuntimeException(1, 30);
                logger.info("Comment with {} commentId was successfully saved but not delivered yet", retVal.getCommentId());
            } catch (RuntimeException ex) {
                if (notificationService.getById(retVal.getNotification().getNotificationId()) != null) {
                    notificationService.delete(retVal.getNotification().getNotificationId());
                }
                commentService.delete(retVal.getCommentId());
                logger.warn("Comment with {} commentId and Notification with {} notificationId was deleted ", retVal.getCommentId(), retVal.getNotification().getNotificationId());
            }
        }
    }

    @AfterReturning(
            pointcut = "execution(* am.ceocompany.springbootdocker.service.NotificationService.save(..))",
            returning = "retVal"
    )
    public void doSomeWorkOnNotification(NotificationEntity retVal) throws InterruptedException {
        if (notificationService.getById(retVal.getNotificationId()) != null) {
            try {
                sleepAndRandomThrowRuntimeException(2, 10);
                retVal.setTime(LocalDateTime.now());
                retVal.setDelivered(true);
                retVal = notificationService.updateNotification(retVal);
                logger.warn("Notification for comment with {} commentId is delivered ", retVal.getComment().getCommentId());
            } catch (RuntimeException ex) {
                retVal.setTime(LocalDateTime.now());
                retVal.setDelivered(false);
                retVal = notificationService.updateNotification(retVal);
                logger.warn("Notification with {} notificationId for comment with {} commentId not delivered ", retVal.getNotificationId(), retVal.getComment().getCommentId());
            }
        }
    }
}
