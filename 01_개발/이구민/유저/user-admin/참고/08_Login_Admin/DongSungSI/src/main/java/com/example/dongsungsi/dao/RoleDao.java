package com.example.dongsungsi.dao;

import com.example.dongsungsi.model.ERole;
import com.example.dongsungsi.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * packageName : com.example.taegyungsi.dao
 * fileName : RoleDao
 * author : kangtaegyung
 * date : 2022/05/23
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23         kangtaegyung          최초 생성
 */
@Mapper
public interface RoleDao {
    Optional<Role> findByName(ERole name);
}
