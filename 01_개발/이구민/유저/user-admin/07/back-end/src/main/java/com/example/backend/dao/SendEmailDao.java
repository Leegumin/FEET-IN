package com.example.backend.dao;

import com.example.backend.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName : com.example.backend.dao
 * fileName : SendEmailDao
 * author : gumin
 * date : 2022-07-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-07         gumin          최초 생성
 */
@Mapper
public interface SendEmailDao {
    User findID(String name, String email);
}
