package com.example.backend.service;

import com.example.backend.model.Category;

import java.util.List;

/**
 * packageName : com.example.taegyungupload.service
 * fileName : CategoryService
 * author : bagjongho
 * date : 2022/07/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/07         bagjongho          최초 생성
 */
public interface CategoryService {

    List<Category> findCat();

}
