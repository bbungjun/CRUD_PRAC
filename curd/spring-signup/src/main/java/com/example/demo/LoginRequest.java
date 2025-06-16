package com.example.demo;

/**
 * 로그인 요청을 처리하기 위한 DTO 클래스
 */
public class LoginRequest {
    private String email;
    private String password;

    // 기본 생성자 (Jackson이 JSON을 객체로 변환하기 위해 필요)
    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
