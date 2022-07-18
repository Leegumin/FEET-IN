package com.example.backend.dao;

import com.example.backend.model.Product;
import com.example.backend.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    Product findById(Long id);
//    todo:
    List<Product> findAll(Criteria criteria);

    List<Product> findByTitleContaining(Criteria criteria);

    List<Product> findByCategory(Criteria criteria);

    List<Product> findBySale(Criteria criteria);

    List<Product> findNewProduct(Criteria criteria);

    List<Product> findRandomProduct(Criteria criteria);

    List<Product> findRandomProductImg(Criteria criteria);

    long updateProduct(Product product);

    int selectTotalCount(String title);
    int selectTotalCount();
    int save(Product product);

    //    id로 회원 삭제 메소드
    int deleteProduct(Long id);
    //    전체 회원 삭제 메소드
    int deleteAll();
}
