package com.example.backend.controller;

import com.example.backend.message.ResponseOrder;
import com.example.backend.model.Order;
import com.example.backend.paging.OrderCriteria;
import com.example.backend.service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
// 기본 보안 주소 허용 : http://localhost:8080
@CrossOrigin(origins = "http://localhost:8080")
// @RestController : 통신을 json형태로 주고받고 싶을때 사용
@RestController
// @RequestMapping("/api") : http://localhost:8000/api
@RequestMapping("/api")
public class OrderController {

    //    logger 찍기를 위한 객체 만듬
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //  @Autowired : 스프링 객체 하나 받아오기
    @Autowired
    private OrderServiceImpl orderService; // 객체정의(null => 스프링객체)

    //  Todo:
    @GetMapping("/list")
    public ResponseEntity<List<ResponseOrder>> getLists(OrderCriteria orderCriteria) {
        logger.info("userId {} ", orderCriteria);

        List<ResponseOrder> files = orderService.findMyOrder(orderCriteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getProductId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
            return new ResponseOrder(
                    dbFile.getId(),
                    dbFile.getProductId(),
                    dbFile.getUserId(),
                    dbFile.getFinalPrice(),
                    dbFile.getShoseSize(),
                    dbFile.getQuantity(),
                    dbFile.getOrderTime(),
                    dbFile.getOrderName(),
                    dbFile.getAddress(),
                    dbFile.getTel(),
                    orderCriteria.getPage(),
                    orderCriteria.getTotalItems(),
                    orderCriteria.getTotalPages(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/list-detail/{id}")
    public ResponseEntity<ResponseOrder> getListDetail(@PathVariable("id") Long id) {

        Order dbFile = orderService.getOrderDetail(id);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/products/")
                .path(String.valueOf(dbFile.getProductId()))
                .toUriString();

        ResponseOrder resFile =  new ResponseOrder(
                dbFile.getId(),
                dbFile.getProductId(),
                dbFile.getUserId(),
                dbFile.getFinalPrice(),
                dbFile.getShoseSize(),
                dbFile.getQuantity(),
                dbFile.getOrderTime(),
                dbFile.getOrderName(),
                dbFile.getAddress(),
                dbFile.getTel(),
                0,
                0,
                0,
                dbFile.getTitle(),
                dbFile.getModel(),
                dbFile.getPrice(),
                dbFile.getDiscount(),
                fileDownloadUri,
                dbFile.getType(),
                dbFile.getData().length);

        return ResponseEntity.status(HttpStatus.OK).body(resFile);
    }

    @DeleteMapping("/order/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") Long id) {

        try {
            orderService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }
}











