package com.example.backend.dao;

import com.example.backend.model.Product;
import com.example.backend.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    // 상품조회
    Product findById(Long id);
    List<Product> findAll(Criteria criteria);
    List<Product> findByTitleContaining(Criteria criteria);
    List<Product> findByCategory(Criteria criteria);
    List<Product> findBySale(Criteria criteria);
    List<Product> findNewProduct(Criteria criteria);
    List<Product> findRandomProduct(Criteria criteria);
    List<Product> findByBestProduct(Criteria criteria);
    List<Product> findRandomProductImg(Criteria criteria);

    // 상품 카운트
    int selectTotalCount(String title);
    int selectCategoryCount(String catId);
    int selectSaleCount(String saleYn);

    // 저장, 수정, 삭제
    int save(Product product);
    long updateProduct(Product product);
    int deleteProduct(Long id);
    int deleteAll();
}
