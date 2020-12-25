package am.ceocompany.springbootdocker.controller;

import am.ceocompany.springbootdocker.dao.CommentDao;
import am.ceocompany.springbootdocker.entity.CommentEntity;
import am.ceocompany.springbootdocker.entity.NotificationEntity;
import am.ceocompany.springbootdocker.mapper.CommentMapper;
import am.ceocompany.springbootdocker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentDao commentDao) {
        CommentEntity commentEntity = commentMapper.toEntity(commentDao);
        commentEntity = commentService.save(commentEntity);
        if(commentEntity.getNotification().getDelivered()){
            CommentEntity commentEntity1 = new CommentEntity();
            commentEntity1.setCommentId(commentEntity.getCommentId());
            commentEntity1.setComment(commentEntity.getComment());
            commentEntity1.setTime(commentEntity.getTime());
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setDelivered(true);
            commentEntity1.setNotification(notificationEntity);
            return ResponseEntity.ok(commentMapper.toDto(commentEntity1));
        }else{
            CommentEntity commentEntity1 = new CommentEntity();
            commentEntity1.setCommentId(commentEntity.getCommentId());
            commentEntity1.setComment(commentEntity.getComment());
            commentEntity1.setTime(commentEntity.getTime());
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setDelivered(false);
            commentEntity1.setNotification(notificationEntity);
            return ResponseEntity.status(201).body(commentMapper.toDto(commentEntity1));
        }
    }

    @GetMapping
    public ResponseEntity<List<CommentDao>> getCommentList(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
        List<CommentEntity> commentEntities = commentService.get(page, size);
        if (commentEntities != null) {
            return ResponseEntity.ok(commentMapper.toDtoList(commentEntities));
        }
        return ResponseEntity.notFound().build();
    }
}
