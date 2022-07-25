package com.example.backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseFileCart {
    //  todo: private String id;
    private Long id;
    private Long userId;
    private Long productId;
    private Long quantity;
    private Long shoseSize;
    private Long finalPrice;
    private Integer page;
    private Integer totalItems;
    private Integer totalPages;
    private String url;
    private String type;
    private Integer size;
    private String title;
    private String model;
}

