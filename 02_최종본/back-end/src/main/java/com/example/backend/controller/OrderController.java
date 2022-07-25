package com.example.backend.controller;

import com.example.backend.message.ResponseOrder;
import com.example.backend.model.Like;
import com.example.backend.model.Order;
import com.example.backend.paging.Criteria;
import com.example.backend.paging.OrderCriteria;
import com.example.backend.service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/list")
    public ResponseEntity<List<ResponseOrder>> getLists(OrderCriteria orderCriteria) {
        logger.info("userId {} ", orderCriteria);

        List<ResponseOrder> files = orderService.findMyOrder(orderCriteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getProductId()))
                    .toUriString();

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
            orderService.deleteOrder(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/order")
    public ResponseEntity<Object>
    createOrder(@RequestBody Order order) {
        logger.info("order : {}" , order);
        boolean savedOrder = orderService.save(order).isPresent();

        try {
            return new ResponseEntity<Object>(savedOrder, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}
