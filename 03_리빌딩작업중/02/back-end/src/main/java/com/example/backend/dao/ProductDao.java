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
    // 상품 이름으로 조회
    List<Product> findByTitleContaining(ProductCriteria productCriteria);
    // 상품 카테고리로 조회
    List<Product> findByCategory(ProductCriteria productCriteria);
    // 세일 상품 조회
    List<Product> findBySale(ProductCriteria productCriteria);
    // 최근 상품 조회
    List<Product> findNewProduct(ProductCriteria productCriteria);
    // 랜덤 상품 조회
    List<Product> findRandomProduct(ProductCriteria productCriteria);
    // 랜덤 상품 이미지 조회
    List<Product> findRandomProductImg(ProductCriteria productCriteria);
    // 베스트 상품 조회
    List<Product> findByBestProduct(ProductCriteria productCriteria);
    // 상품 전체 개수 카운트
    int selectTotalCount(String title);
    // 상품 카테고리별 개수 카운트
    int selectCategoryCount(String catId);
    // 세일 상품 카운트
    int selectSaleCount(String saleYn);

    // 상품 등록
    int save(Product product);
    // 상품 수정
    long updateProduct(Product product);
    // 상품 삭제
    int deleteProduct(Long id);
    // 상품 전체삭제
    int deleteAll();
}
