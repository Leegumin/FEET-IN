package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.taegyungupload.model
 * fileName : Order
 * author : kimjuyeong
 * date : 2022/07/12
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/12         kimjuyeong          최초 생성
 */
@Getter
@Setter
@ToString
public class Order {
    private Long id;
    private Long productId;
    private Long finalPrice;
    private Long shoseSize;
    private Long quantity;
    private String orderTime;
    private String orderName;
    private String address;
    private String tel;
}
