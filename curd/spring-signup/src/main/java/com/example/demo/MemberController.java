package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 모든 회원 목록 조회
     * 
     * @return 회원 목록
     */
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Member member) {
        try {
            Member saved = memberService.register(member);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * 로그인 API 엔드포인트
     * 
     * @param loginRequest 로그인 요청 데이터 (email, password)
     * @return 로그인 성공 시 회원 정보, 실패 시 오류 메시지
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Member member = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (member != null) {
            // 비밀번호는 응답에서 제외
            member.setPassword(null);
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.badRequest().body(new ErrorResponse("이메일 또는 비밀번호가 일치하지 않습니다."));
        }
    }
}
