package com.example.backend.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// !클라이언트에 메시지를 넘겨주기 위한 객체(오류 등)
@Setter
@Getter
@ToString
public class MessageResponse {
  private String message;

  public MessageResponse(String message) {
    this.message = message;
  }
}
