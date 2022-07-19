package com.example.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.taegyungupload.model
 * fileName : Like
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
@ToString
@NoArgsConstructor
public class Like {
    private Long likeId;
    private int userId;
    private int productId;
    private int likeCount;

    // product
    private Long id;
    private String title;
    private String model;
    private int price;
    private int discount;
    private String saleYn;
    private String name;
    private String type;
    private byte[] data;
}
