package com.example.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Product {
  private Long id;

  private String title;
  //  todo: String title, String content,
  private String model;

  private String description;

  private long price;

  private String name;

  private String category;

  private String insertTime;

  private String updateTime;

  private String deleteTime;

  private String deleteYn;

  private int percent;
  private int discount;
  private String saleYn;

  private String type;

  //  BLOB 데이터형으로 저장
  private byte[] data;

  //  todo: String title, String content,
  public Product(String name, String title, String model, String description, long price,
                 String category, int discount, int percent, String saleYn, String type, byte[] data) {
    this.name = name;
    this.title = title;
    this.model = model;
    this.description = description;
    this.price = price;
    this.category = category;
    this.percent = percent;
    this.discount = discount;
    this.saleYn = saleYn;
    this.type = type;
    this.data = data;
  }

}
