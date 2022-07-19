package com.example.backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseOrder {
    private Long id;
    private int productId;
    private int userId;
    private int finalPrice;
    private int shoseSize;
    private int quantity;
    private String orderTime;
    private String orderName;
    private String address;
    private String tel;

    // 페이징
    private Integer page;
    private Integer totalItems;
    private Integer totalPages;

    // product
    private String title;
    private String model;
    private String price;
    private String discount;
    private String url;
    private String type;
    private Integer size;

}

