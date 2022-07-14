package com.example.backend.dao;

import com.example.backend.model.FileDB;
import com.example.backend.paging.ReviewCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileDBDao {
    FileDB findById(Long reviewId);

    List<FileDB> findAll(ReviewCriteria criteria);

    List<FileDB> findByTitleContaining(ReviewCriteria criteria);

    List<FileDB> findByProduct(ReviewCriteria criteria);


    int productReviewTotalCount(Long id);

    int selectTotalCount(String title);
    int selectTotalCount();
    int save(FileDB fileDB);

    //    id로 회원 삭제 메소드
    int deleteBoard(Long reviewId);
    //    전체 회원 삭제 메소드
    int deleteAll();

}
