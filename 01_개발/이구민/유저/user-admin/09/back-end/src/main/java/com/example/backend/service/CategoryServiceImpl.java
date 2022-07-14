package com.example.backend.service;

import com.example.backend.dao.CategoryDao;
import com.example.backend.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : com.example.taegyungupload.service
 * fileName : CategoryServiceImpl
 * author : bagjongho
 * date : 2022/07/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/07         bagjongho          최초 생성
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Category> findCat() {
        return categoryDao.findCat();
    }
}
