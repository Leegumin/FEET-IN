package com.example.backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName : com.example.taegyungupload.message
 * fileName : ResponseLike
 * author : bagjongho
 * date : 2022/07/15
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15         bagjongho          최초 생성
 */
@Setter
@Getter
@AllArgsConstructor
public class ResponseLike {
    private Long likeId;
    private int userId;
    private int productId;

    // product
    private Long id;
    private String title;
    private String model;
    private int price;
    private int discount;
    private String saleYn;
    private String url;
    private String type;
    private Integer size;
}
