package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Member register(Member member) {
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        return memberRepository.save(member);
    }

    /**
     * 로그인 처리 메서드
     * 
     * @param email    이메일
     * @param password 비밀번호
     * @return 로그인 성공 시 회원 정보, 실패 시 null
     */
    public Member login(String email, String password) {
        // 이메일로 회원 찾기
        Optional<Member> memberByEmail = memberRepository.findByEmail(email);

        // 회원이 존재하고 비밀번호가 일치하면 회원 정보 반환
        if (memberByEmail.isPresent() && memberByEmail.get().getPassword().equals(password)) {
            return memberByEmail.get();
        }

        // 이메일이 사용자 이름일 수도 있으므로 사용자 이름으로도 시도
        Optional<Member> memberByUsername = memberRepository.findByUsername(email);
        if (memberByUsername.isPresent() && memberByUsername.get().getPassword().equals(password)) {
            return memberByUsername.get();
        }

        // 로그인 실패
        return null;
    }

    /**
     * 모든 회원 목록 조회
     * 
     * @return 모든 회원 목록
     */
    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
