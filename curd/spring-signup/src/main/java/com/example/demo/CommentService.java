package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    /**
     * 특정 게시글에 댓글 작성
     * 
     * @param postId  게시글 ID
     * @param comment 댓글 객체
     * @return 저장된 댓글 객체
     */
    @Transactional
    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다. ID: " + postId));

        comment.setPost(post);
        return commentRepository.save(comment);
    }

    /**
     * 특정 게시글의 모든 댓글 조회
     * 
     * @param postId 게시글 ID
     * @return 댓글 목록
     */
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
    }

    /**
     * 댓글 삭제
     * 
     * @param commentId 댓글 ID
     * @param author    댓글 작성자 (권한 확인용)
     */
    @Transactional
    public void deleteComment(Long commentId, String author) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("해당 댓글이 존재하지 않습니다. ID: " + commentId));

        // 작성자 확인 (본인이 작성한 댓글만 삭제 가능)
        if (!comment.getAuthor().equals(author)) {
            throw new IllegalArgumentException("댓글 삭제 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }
}
