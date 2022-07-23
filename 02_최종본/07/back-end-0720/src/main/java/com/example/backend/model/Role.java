package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Role {

    // 권한 번호
    private Integer id;
    // 권한 이름
    private ERole   name;

}