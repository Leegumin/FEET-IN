package com.example.backend.dao;

import com.example.backend.model.Order;
import com.example.backend.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderDao {


//	Todo : 2개수정(findByTitleContaining, findAll) , 1개 추가(selectTotalCount)
	public List<Order> findAll(Criteria criteria);

//	제목에 따른 데이터 건수를 세는 메소드
	int selectTotalCount(String orderName);

	public Optional<Order> findById(Long id);

	public int insertOrder(Order order);

	public int updateOrder(Order order);


	public int deleteById(Long id);

	public int deleteAll();

}