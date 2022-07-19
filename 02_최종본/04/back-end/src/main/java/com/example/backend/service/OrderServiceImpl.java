package com.example.backend.service;

import com.example.backend.dao.OrderDao;
import com.example.backend.model.Order;
import com.example.backend.paging.OrderCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = orderDao.deleteById(id);

        return (queryResult >=1) ? true : false;
    }
}











