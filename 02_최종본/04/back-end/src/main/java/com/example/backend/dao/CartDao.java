package com.example.backend.dao;

import com.example.backend.model.Cart;
import com.example.backend.paging.CartCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartDao {

	boolean checkCart(Long productId);

	int deleteByProductId(Long productId);

//	Todo : 2개수정(findByTitleContaining, findAll) , 1개 추가(selectTotalCount)
	public List<Cart> findAll(CartCriteria criteria);

//	제목에 따른 데이터 건수를 세는 메소드
int selectTotalCart(Long productId);
	int selectTotalCart();

	Cart findById(Long id);

	List<Cart> findByCart(CartCriteria criteria);

	public int insertCart(Cart cart);

	int CartTotalCount(Long id);

	public Optional<Cart> findById2(Long id);

	public int updateCart(Cart cart);


	public int insertCartOrder(Cart cart);

	public int deleteById(Long id);

	public int deleteAll();

}