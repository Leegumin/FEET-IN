package com.example.taegyungsi.model;

import lombok.Data;

/**
 * packageName : com.example.taegyungsi.model
 * fileName : MessageResponse
 * author : gumin
 * date : 2022-06-17
 * description : 클라이언트(Vue)로 전송할 응답 메세지 클래스(객체)
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-17         gumin          최초 생성
 */
// 서버쪽으로 응답을 보냄
@Data
public class MessageResponse {
    private String message;

    // 생성자
    public MessageResponse(String message) {
        this.message = message;
    }
}
