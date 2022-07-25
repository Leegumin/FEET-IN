package com.example.backend.dao;

import com.example.backend.model.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName : com.example.taegyungupload.dao
 * fileName : LikeDao
 * author : bagjongho
 * date : 2022/07/15
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15         bagjongho          최초 생성
 */
@Mapper
public interface LikeDao {
    List<Like> findAll(Like like);

    int checkLikeYn(Like like);

    List<Like> findMyLike(Like like);

    int addLike(Like like);

    int deleteLike(Like like);
}
