package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.backend.model
 * fileName : SendToMeDto
 * author : gumin
 * date : 2022-07-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-07         gumin          최초 생성
 */
@Getter
@Setter
@ToString
public class SendEmailDto {
  private String email;
  private String name;


}
