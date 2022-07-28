package com.example.backend.dao;

import com.example.backend.model.Faq;
import com.example.backend.paging.ProductCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FaqDao {

    Optional <Faq> findById(Long id);

    List<Faq> findByTitleContaining(ProductCriteria productCriteria);

    List<Faq> findAll(ProductCriteria productCriteria);

    int selectTotalCount(String title);

    long insertFaq(Faq faq);

    long updateFaq(Faq faq);

    long deleteFaq(Long id);

    long deleteAll();
}
