package com.example.backend.dao;

import com.example.backend.model.Order;
import com.example.backend.paging.Criteria;
import com.example.backend.paging.OrderCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderDao {

	List<Order> findMyOrder(OrderCriteria orderCriteria);

	Order getOrderDetail(Long id);

	int selectTotalCount(int userId);

	int deleteById(Long id);

	int insertOrder(Order order);

	Optional<Order> findById(Long id);

}