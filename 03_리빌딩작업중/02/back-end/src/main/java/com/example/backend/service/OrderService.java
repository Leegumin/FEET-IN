package com.example.backend.service;

import com.example.backend.model.Order;
import com.example.backend.paging.OrderCriteria;

import java.util.Optional;
import java.util.stream.Stream;

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

    public Stream<Order> findMyOrder(OrderCriteria orderCriteria);

    public Order getOrderDetail(Long id);

    boolean deleteOrder(Long id);

    Optional<Order> save(Order order);
}
