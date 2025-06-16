package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    /**
     * 모든 게시글 조회
     * 
     * @return 게시글 목록
     */
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * 특정 게시글 조회
     * 
     * @param id 게시글 ID
     * @return 게시글 객체
     */
    @Transactional
    public Post getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다. ID: " + id));

        // 조회수 증가
        post.setViews(post.getViews() + 1);
        return postRepository.save(post);
    }

    /**
     * 게시글 작성
     * 
     * @param post 게시글 객체
     * @return 저장된 게시글 객체
     */
    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * 게시글 수정
     * 
     * @param id          게시글 ID
     * @param updatedPost 수정할 게시글 정보
     * @param author      게시글 작성자 (권한 확인용)
     * @return 수정된 게시글 객체
     */
    @Transactional
    public Post updatePost(Long id, Post updatedPost, String author) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다. ID: " + id));

        // 작성자 확인 (본인이 작성한 게시글만 수정 가능)
        if (!post.getAuthor().equals(author)) {
            throw new IllegalArgumentException("게시글 수정 권한이 없습니다.");
        }

        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());

        return postRepository.save(post);
    }

    /**
     * 게시글 삭제
     * 
     * @param id     게시글 ID
     * @param author 게시글 작성자 (권한 확인용)
     */
    @Transactional
    public void deletePost(Long id, String author) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다. ID: " + id));

        // 작성자 확인 (본인이 작성한 게시글만 삭제 가능)
        if (!post.getAuthor().equals(author)) {
            throw new IllegalArgumentException("게시글 삭제 권한이 없습니다.");
        }

        postRepository.delete(post);
    }
}
