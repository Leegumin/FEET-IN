package com.example.backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseFileReview {
    //  todo: private String id;
    private Long id;
    private Long reviewId;
    private String name;
    //  todo: title, content
    private String title;
    private String content;
    private String writer;
    private Long starNum;
    private String insertTime;
    private Integer page;
    private Integer totalItems;
    private Integer totalPages;
    private String url;
    private String type;
    private Integer size;

}

