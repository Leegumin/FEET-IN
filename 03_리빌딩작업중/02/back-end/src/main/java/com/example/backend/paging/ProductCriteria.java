package com.example.backend.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// !상품 페이징 처리를 위한 객체
@Setter
@Getter
@ToString
public class ProductCriteria {

    // 현재 페이지 번호
    private Integer page;
    // 1페이지당 출력할 데이터 개수
    private Integer size;
    // 총 상품 개수
    private Integer totalItems;
    // 총 페이지 수
    private Integer totalPages;
    // 상품 이름
    private String title;
    private String id;
    // 상품 세일 여부
    private String saleYn;
    // 상품 카테고리 번호
    private String catId;

    private int rowNum;

    public ProductCriteria() {
        this.page = 0;
        this.size = 12;
    }


}










