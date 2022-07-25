package com.example.backend.service;

import com.example.backend.dao.OrderDao;
import com.example.backend.model.Order;
import com.example.backend.model.Product;
import com.example.backend.paging.Criteria;
import com.example.backend.paging.OrderCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    @Override
    public Stream<Order> findMyOrder(OrderCriteria orderCriteria) {


        int totalCount = orderDao.selectTotalCount(orderCriteria.getUserId());

        orderCriteria.setTotalItems(totalCount);
        orderCriteria.setTotalPages(totalCount / orderCriteria.getSize());

        logger.info(" service userId {}", orderCriteria);

        return orderDao.findMyOrder(orderCriteria).stream();

    }

    public Order getOrderDetail(Long id) {
        return orderDao.getOrderDetail(id);
    }


    @Override
    public boolean deleteOrder(Long id) {
        int queryResult = 0;

        queryResult = orderDao.deleteById(id);

        return (queryResult >=1) ? true : false;

    }

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

//        insert 문 후에는 customer의 id속성에 값이 저장됨(<selectkey>)
        seqId = order.getId();
        logger.info("seqId {}", seqId);

        return orderDao.findById(seqId);
    }

}











