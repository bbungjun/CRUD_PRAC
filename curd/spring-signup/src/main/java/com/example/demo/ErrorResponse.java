package com.example.demo;

/**
 * API 에러 응답을 위한 DTO 클래스
 */
public class ErrorResponse {
    private String message;

    // 기본 생성자 (Jackson을 위해 필요)
    public ErrorResponse() {
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
