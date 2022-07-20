package com.example.backend.controller;

import com.example.backend.model.Order;
import com.example.backend.paging.Criteria;
import com.example.backend.service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //    모든 회원 조회 메뉴
    @GetMapping("/order")
    public ResponseEntity<Map<String, Object>>
    getAllTitlePage(Criteria criteria) {
        // totalItems, totalPages 계산해서 criteria 객체에 저장
        List<Order> orders = orderService.findAll(criteria);

        try {

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // totalItems, totalPages = 값이 있음

//			Todo : Map에 넣어 객체 + 페이지 정보를 Vue 로 전송
            Map<String, Object> response = new HashMap<>();
            response.put("orders", orders);
            // page : 현재페이지
            response.put("currentPage", criteria.getPage());
            // totalItems : 총 데이터 건수
            response.put("totalItems", criteria.getTotalItems());
            // totalPages : 총 페이지 개수
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    회원 생성 메뉴
    @PostMapping("/order")
    public ResponseEntity<Object>
    createOrder(@RequestBody Order order) {
        logger.info("order : {}" , order);
        boolean savedOrder = orderService.save(order).isPresent();

        try {
//            Vue에 객체 + 성공메세지 전송
            return new ResponseEntity<Object>(savedOrder, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            Vue에 보낼 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    //    id로 회원 조회 메뉴
    @GetMapping("/order/{id}")
    public ResponseEntity<Object>
    getOrderById(@PathVariable("id") Long id) {
//        id로 회원 조회 하는 서비스를 호출 ( Optional 떼내기 : get() )
        Order order = orderService.findById(id).get();

        try {
            if (order != null) {
//                성공시 Vue에 객체 + 성공메세지 전송
                return new ResponseEntity<Object>(order, HttpStatus.OK);
            } else {
//                Vue에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
//            Vue에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Object> updateOrder(
            @PathVariable("id") Long id,
            @RequestBody Order order
    ) {
        try {

//            customer에 id값 저장
            order.setId(id);
            Order savedOrder = orderService.save(order).get();
            return new ResponseEntity<Object>(savedOrder, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    //    id로 회원 삭제 메뉴
//    /customers/deletion/{id}
    @PutMapping("/order/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(
            @PathVariable("id") Long id
    ) {
        try {
//            id로 삭제 서비스를 호출(내부적으로 update문이 실행됨)
            orderService.deleteById(id);
//            Vue에 성공메세지
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
// 웹애플리케이션 개발 : 클라이언트(Vue,React,HTML) <-> 서버(SpringBoot, Node)
// Vue(클라이언트) 에 에러메세지를 전송
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

}











