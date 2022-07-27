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
    private String discount;
    private String name;
    private String type;
    private byte[] data;
}
