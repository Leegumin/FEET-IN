package com.example.backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseFile {
    //  todo: private String id;
    private Long id;
    private String name;
    //  todo: title, content
    private String title;
    private String model;
    private String category;
    private String description;
    private Long price;
    private int discount;
    private int percent;
    private String saleYn;
    private String insertTime;
    private Integer page;
    private Integer totalItems;
    private Integer totalPages;
    private String url;
    private String type;
    private Integer size;

}

