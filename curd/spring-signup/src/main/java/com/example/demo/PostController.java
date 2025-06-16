package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    /**
     * 모든 게시글 조회
     * 
     * @return 게시글 목록
     */
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    /**
     * 특정 게시글 조회
     * 
     * @param id 게시글 ID
     * @return 게시글 객체
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        try {
            Post post = postService.getPostById(id);
            return ResponseEntity.ok(post);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * 게시글 작성
     * 
     * @param post 게시글 객체
     * @return 저장된 게시글 객체
     */
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postService.createPost(post);
        return ResponseEntity.ok(savedPost);
    }

    /**
     * 게시글 수정
     * 
     * @param id     게시글 ID
     * @param post   수정할 게시글 정보
     * @param author 게시글 작성자
     * @return 수정된 게시글 객체
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long id,
            @RequestBody Post post,
            @RequestParam String author) {
        try {
            Post updatedPost = postService.updatePost(id, post, author);
            return ResponseEntity.ok(updatedPost);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * 게시글 삭제
     * 
     * @param id     게시글 ID
     * @param author 게시글 작성자
     * @return 삭제 성공 여부
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(
            @PathVariable Long id,
            @RequestParam String author) {
        try {
            postService.deletePost(id, author);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }
}
