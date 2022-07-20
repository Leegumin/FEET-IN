package com.example.backend.service;

import com.example.backend.dao.OrderDao;
import com.example.backend.model.Order;
import com.example.backend.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
// @Service : 스프링에 객체로 생성
@Service
public class OrderServiceImpl implements OrderService {

//    스프링에 customerDao 이미 생성되어 있음
//    생성된 객체를 받아오는 어노테이션 : @Autowired
    @Autowired
    private OrderDao orderDao; // 객체 정의 ( null => 스프링 객체 )

//    나중에 에러 로그 추적을 위해 하나 만들어둠
    Logger logger = LoggerFactory.getLogger(this.getClass());

//    id로 회원 조회 하는 메소드
    @Override
    public Optional<Order> findById(long id) {
        return orderDao.findById(id);
    }

//    모든 회원 조회 서비스
    @Override
    public List<Order> findAll(Criteria criteria) {
        return orderDao.findAll(criteria);
    }


    //    회원 생성 / 수정 서비스 ( insert / update )
    @Override
    public Optional<Order> save(Order order) {
//        db 시퀀스 번호 저장을 위한 변수
        long seqId = 0;

//        매개변수 customer 정보 출력
        logger.info("order {}", order);

//        customer.getId() 없으면 insert 문 호출( 새로운 회원 생성 )
        if(order.getId() == null) {
            orderDao.insertOrder(order);
        }
        //                         있으면 update 문 호출
        else {
            orderDao.updateOrder(order);
        }

//        insert 문 후에는 customer의 id속성에 값이 저장됨(<selectkey>)
        seqId = order.getId();
        logger.info("seqId {}", seqId);

        return orderDao.findById(seqId);
    }

    @Override
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = orderDao.deleteById(id);

        return (queryResult >=1) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;

//        delete / update / insert 문은 결과 실행 후
//        정상 실행되면 건수(삭제/수정/생성)가 결과로 리턴(반환)
        queryResult = orderDao.deleteAll();

        return (queryResult >=1) ? true : false;
    }
}











