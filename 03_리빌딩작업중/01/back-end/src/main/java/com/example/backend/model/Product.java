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

  // 상품 등록 시 고유 번호
  private Long id;
  // 상품 이름
  private String title;
  // 상품 모델이름
  private String model;
  // 상품 상세설명
  private String description;
  // 상품 가격
  private long price;
  // 상품 카테고리
  private String category;
  // 상품 등록날짜
  private String insertTime;
  // 상품 수정날짜
  private String updateTime;
  // 상품 삭제날짜
  private String deleteTime;
  // 상품 삭제 여부
  private String deleteYn;
  // 상품 할인율
  private int percent;
  // 상품 할인가격
  private int discount;
  // 상품 세일여부
  private String saleYn;
  // BLOB 데이터
  private byte[] data;
  // BLOB 데이터 타입
  private String type;
  // 이미지 파일 이름
  private String name;

  // 상품 저장에 사용되는 생성자 ( ProductService -> store )
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
