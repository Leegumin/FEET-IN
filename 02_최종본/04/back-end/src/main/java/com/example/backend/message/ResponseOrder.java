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

    // product
    private String title;
    private String model;
    private String price;
    private String url;
    private String type;
    private Integer size;

}

