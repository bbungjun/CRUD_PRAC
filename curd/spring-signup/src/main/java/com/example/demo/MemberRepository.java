package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);

    // 사용자 이름으로 회원 찾기
    Optional<Member> findByUsername(String username);

    // 이메일로 회원 찾기 (이메일로 로그인하는 경우)
    Optional<Member> findByEmail(String email);
}
