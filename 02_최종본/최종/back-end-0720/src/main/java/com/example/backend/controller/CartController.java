package com.example.backend.controller;

import com.example.backend.message.ResponseFileCart;
import com.example.backend.model.Cart;
import com.example.backend.paging.CartCriteria;
import com.example.backend.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
public class CartController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CartService cartService;


    @GetMapping("/cart")
    public ResponseEntity<List<ResponseFileCart>> fintAll(CartCriteria criteria) {
        logger.info("criteria title{} : ", criteria.getSearchTitle());
        logger.info("criteria page{} : ", criteria.getPage());
        logger.info("criteria size{} : ", criteria.getSize());

        List<ResponseFileCart> files = cartService.findAll(criteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/cartImage/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFileCart(
                    dbFile.getId(),
                    dbFile.getUserId(),
                    dbFile.getProductId(),
                    dbFile.getQuantity(),
                    dbFile.getShoseSize(),
                    dbFile.getFinalPrice(),
                    criteria.getPage(),
                    criteria.getTotalItems(),
                    criteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getTitle(),
                    dbFile.getModel());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable long id) {
        Cart carts = cartService.findById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + carts.getName() + "\"")
                .body(carts.getData2());
    }

    //
    @GetMapping("/cart/")
    public ResponseEntity<List<ResponseFileCart>> findByCart(CartCriteria criteria) {
        logger.info("findByCart logger {}" , criteria);

        List<ResponseFileCart> files = cartService.findByCart(criteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/cart/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFileCart(
                    dbFile.getId(),
                    dbFile.getUserId(),
                    dbFile.getProductId(),
                    dbFile.getQuantity(),
                    dbFile.getShoseSize(),
                    dbFile.getFinalPrice(),
                    criteria.getPage(),
                    criteria.getTotalItems(),
                    criteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getTitle(),
                    dbFile.getModel());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @PostMapping("/cart")
    public ResponseEntity<Object>
    createCart(@RequestBody Cart cart) {
        logger.info("cart : {}", cart);
        boolean savedCart = cartService.save(cart).isPresent();

        try {
            return new ResponseEntity<Object>(savedCart, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cartorder")
    public ResponseEntity<Object>
    insertCart(@RequestBody Cart cart) {
        logger.info("cart : {}", cart);
        boolean savedCart = cartService.saveOrder(cart).isPresent();

        try {

            return new ResponseEntity<Object>(savedCart, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updatecart/{id}")
    public ResponseEntity<Object> updateCart(
            @PathVariable("id") Long id,
            @RequestBody Cart cart
    ) {
        try {
            cart.setId(id);
            Cart saveCart = cartService.save(cart).get();
            return new ResponseEntity<Object>(saveCart, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cart/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteCart(
            @PathVariable("id") Long id
    ) {
        try {
            cartService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

}











