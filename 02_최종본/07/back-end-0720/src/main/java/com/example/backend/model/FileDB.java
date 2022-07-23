package com.example.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class FileDB {

  private Long id;
  private Long reviewId;
  private String name;
  private String title;
  private String content;
  private String writer;
  private long starNum;
  private String insertTime;
  private String updateTime;
  private String deleteTime;
  private String deleteYn;
  private String type;
//  BLOB 데이터형으로 저장
  private byte[] data;
  private byte[] data2;

//  todo: String title, String content,
  public FileDB(Long id, String name, String title, String content, String writer, long starNum, String type, byte[] data) {
    this.id = id;
    this.name = name;
    this.title = title;
    this.content = content;
    this.writer = writer;
    this.starNum = starNum;
    this.type = type;
    this.data = data;
  }
}
