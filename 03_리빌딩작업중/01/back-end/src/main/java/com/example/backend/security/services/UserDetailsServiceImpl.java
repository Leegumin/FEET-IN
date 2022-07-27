package com.example.backend.security.services;

import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName : com.example.backend.dao
 * fileName : UserDao
 * author : gumin
 * date : 2022/06/23
 * description : 유저 정보 가져오는 서비스, WebSecurityConfig에서 사용됨
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/23         gumin          최초 생성
 */

@Service
// *UserDetailsService : 유저 정보를 가져오기 위한 서비스
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserDao userDao;

  @Override
  // *@Transactional : 트랜잭션 기능이 포함된 프록시 객체가 생성되어 자동으로 commit 혹은 rollback을 진행
  // *트랜잭션 : 데이터베이스의 상태를 변화시키기 해서 수행하는 작업의 단위를 뜻한다.
  // *userDao.findByUserName(username) : username(유저 로그인 id)로 유저 정보를 찾아 user에 저장함
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.findByUserName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    // *HashSet : 증복을 허용하지 않는 클래스
    Set<String> roles = new HashSet<>();
    // roles에 가져온 유저 정보중 권한 추가
    roles.add(user.getRole());
    // 추가된 roles를 유저의 roles에 다시 저장
    user.setRoles(roles);

    // 빌드 패턴으로 UserDetailsImpl에 유저 정보를 집어 넣어 생성
    // *빌드 패턴 :  Builder 클래스를 만들어 필수 값에 대해서는 생성자를 통해, 선택적인 값들에 대해서는 메소드를 통해
    // *step-by-step으로 값을 입력받은 후에 build() 메소드를 통해 최종적으로 하나의 인스턴스를 리턴하는 방식
    return UserDetailsImpl.build(user);
  }

}
