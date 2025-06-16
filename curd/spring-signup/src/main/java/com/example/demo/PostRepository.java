package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 기본 CRUD 메소드는 JpaRepository에서 제공됨
}
