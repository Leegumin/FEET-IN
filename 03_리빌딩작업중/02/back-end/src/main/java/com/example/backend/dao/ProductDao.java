package com.example.backend.dao;

import com.example.backend.model.Product;
import com.example.backend.paging.ProductCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    // 상품 고유키로 상품조회
    Product findById(Long id);
    // 상품 전체 조회
    List<Product> findAll(ProductCriteria productCriteria);
    List<Product> findByTitleContaining(ProductCriteria productCriteria);
    List<Product> findByCategory(ProductCriteria productCriteria);
    List<Product> findBySale(ProductCriteria productCriteria);
    List<Product> findNewProduct(ProductCriteria productCriteria);
    List<Product> findRandomProduct(ProductCriteria productCriteria);
    List<Product> findByBestProduct(ProductCriteria productCriteria);
    List<Product> findRandomProductImg(ProductCriteria productCriteria);

    // 상품 전체 개수 카운트
    int selectTotalCount(String title);
    // 상품 카테고리별 개수 카운트
    int selectCategoryCount(String catId);
    // 세일 상품 카운트
    int selectSaleCount(String saleYn);

    // 저장, 수정, 삭제
    int save(Product product);
    long updateProduct(Product product);
    int deleteProduct(Long id);
    int deleteAll();
}
