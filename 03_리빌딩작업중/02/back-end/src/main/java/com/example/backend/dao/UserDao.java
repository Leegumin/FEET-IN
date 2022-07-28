package com.example.backend.dao;

import com.example.backend.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * packageName : com.example.backend.dao
 * fileName : UserDao
 * author : gumin
 * date : 2022/06/23
 * description : 유저 관련 데이터 엑세스 오브젝트
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/23         gumin          최초 생성
 */
@Mapper
public interface UserDao {

    // id로 유저 가져오기 메소드
    User findById(String id);

    // UserName(유저 로그인 id)로 찾기
    Optional<User> findByUserName(String username);

    // 존재하는 유저 UserName(유저 로그인 id) 찾기
    int existsByUserName(String username);

    // 존재하는 Email 찾기
    int existsByEmail(String email);

    // 회원가입
    int insertUser(User user);

    // 유저 회원탈퇴. delete(update)
    int deleteUser(Long id);

    // 회원수정 : update
    int updateUser(User user);
}
