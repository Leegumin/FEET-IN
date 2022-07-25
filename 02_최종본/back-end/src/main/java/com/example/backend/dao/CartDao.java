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

	public List<Cart> findAll(CartCriteria criteria);

	int selectTotalCart();

	Cart findById(Long id);

	List<Cart> findByCart(CartCriteria criteria);

	int insertCart(Cart cart);

	int CartTotalCount(CartCriteria criteria);

	Optional<Cart> findById2(Long id);

	int updateCart(Cart cart);


	int insertCartOrder(Cart cart);

	int deleteById(Long id);

	int deleteAll();

}