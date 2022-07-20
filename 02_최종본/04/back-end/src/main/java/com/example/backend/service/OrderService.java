package com.example.backend.service;

import com.example.backend.model.Order;
import com.example.backend.paging.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerService
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
public interface OrderService {
//    id로 조회하는 메소드(결과 : 1건)
    Optional<Order> findById(long id);

//    모든 회원 조회하는 메소드(결과 : 여러건)
    List<Order> findAll(Criteria criteria);


    //    회원 저장 / 수정하는 메소드( insert / update )
    Optional<Order> save(Order order);

//    id로 회원 삭제하는 메소드
    boolean deleteById(Long id);

//    모든 회원 삭제하는 메소드
    boolean deleteAll();
}











