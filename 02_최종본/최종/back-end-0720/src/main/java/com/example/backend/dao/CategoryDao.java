package com.example.backend.dao;

import com.example.backend.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {

    List<Category> findCat();

}
