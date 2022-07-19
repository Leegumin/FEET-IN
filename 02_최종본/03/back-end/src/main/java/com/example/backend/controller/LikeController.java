package com.example.backend.controller;

import com.example.backend.message.ResponseLike;
import com.example.backend.model.Like;
import com.example.backend.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : com.example.customerspring.controller
 * fileName : CustomerController
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
@Controller
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api")
public class LikeController {

    @Autowired
    private LikeService likeService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/likes")
    public ResponseEntity<List<ResponseLike>> getLikeCount(Like like) {
        logger.info("get Like {}", like);
        List<ResponseLike> resFile = likeService.showLikeCount(like).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getProductId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
            return new ResponseLike (
                    dbFile.getLikeId(),
                    dbFile.getUserId(),
                    dbFile.getProductId(),
                    dbFile.getId(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    dbFile.getSaleYn(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(resFile);
    }

    @PostMapping("/likeyn")
    public ResponseEntity<Object> checkLikeYn(@RequestBody Like like) {
        logger.info("check like{}", like);

        boolean checkLike = likeService.checkLikeYn(like);

        try {
            return new ResponseEntity<Object>(checkLike, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            Vue에 보낼 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mylikes")
    public ResponseEntity<List<ResponseLike>> getMyLike(Like like) {
        logger.info("get Like {}", like);
        List<ResponseLike> resFile = likeService.findMyLike(like).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getProductId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
            return new ResponseLike (
                    dbFile.getLikeId(),
                    dbFile.getUserId(),
                    dbFile.getProductId(),
                    dbFile.getId(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    dbFile.getSaleYn(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(resFile);
    }

    @PostMapping("/like")
    public ResponseEntity<Object> addLike(@RequestBody Like like) {
        logger.info("post Like {} ", like);

        boolean addLike = likeService.addLike(like);

        try {
            return new ResponseEntity<Object>(addLike, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            Vue에 보낼 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/like/deletion")
    public ResponseEntity<Object> deleteLike(@RequestBody Like like) {
        logger.info("delete Like{} ", like);

        boolean deleteLike = likeService.deleteLike(like);

        try {
            return new ResponseEntity<Object>(deleteLike, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}











