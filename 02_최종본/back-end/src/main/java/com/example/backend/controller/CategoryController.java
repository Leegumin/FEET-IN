package com.example.backend.controller;

import com.example.backend.model.Category;
import com.example.backend.service.CategoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName : com.example.taegyungupload.controller
 * fileName : CategoryController
 * author : bagjongho
 * date : 2022/07/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/07         bagjongho          최초 생성
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CategoryController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/category")
    public ResponseEntity<Object> getAllCategory() {

        List<Category> categories = categoryService.findCat();

        try {
            return new ResponseEntity<Object>(categories, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

}
