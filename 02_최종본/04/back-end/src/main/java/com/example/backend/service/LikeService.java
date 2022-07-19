package com.example.backend.service;

import com.example.backend.dao.LikeDao;
import com.example.backend.model.Like;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class LikeService {

    @Autowired
    private LikeDao likeDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Stream<Like> showLikeCount(Like like) {

        return likeDao.findAll(like).stream();
    }

    public boolean checkLikeYn(Like like) {
        int checkResult = 0;

        checkResult = likeDao.checkLikeYn(like);

        return (checkResult >= 1) ? true : false;
    }

    public Stream<Like> findMyLike(Like like) {
        return likeDao.findMyLike(like).stream();
    }

    public boolean addLike(Like like) {
        int addResult = 0;

        addResult = likeDao.addLike(like);

        return (addResult >= 1) ? true : false;
    }

    public boolean deleteLike(Like like) {
        int deleteResult = 0;

        deleteResult = likeDao.deleteLike(like);

        return (deleteResult >= 1) ? true : false;
    }

}
