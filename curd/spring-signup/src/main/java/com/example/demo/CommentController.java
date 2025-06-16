package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 특정 게시글의 댓글 목록 조회
     * 
     * @param postId 게시글 ID
     * @return 댓글 목록
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    /**
     * 댓글 작성
     * 
     * @param postId  게시글 ID
     * @param comment 댓글 객체
     * @return 저장된 댓글 객체
     */
    @PostMapping("/post/{postId}")
    public ResponseEntity<?> createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        try {
            Comment savedComment = commentService.createComment(postId, comment);
            return ResponseEntity.ok(savedComment);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * 댓글 삭제
     * 
     * @param commentId 댓글 ID
     * @param author    댓글 작성자 (요청 바디에서 확인)
     * @return 삭제 성공 여부
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId,
            @RequestParam String author) {
        try {
            commentService.deleteComment(commentId, author);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }
}
