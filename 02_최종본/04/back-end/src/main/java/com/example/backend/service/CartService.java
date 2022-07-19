package com.example.backend.service;

import com.example.backend.dao.CartDao;
import com.example.backend.model.Cart;
import com.example.backend.paging.CartCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
// @Service : 스프링에 객체로 생성
@Service
public class CartService {

//    스프링에 customerDao 이미 생성되어 있음
//    생성된 객체를 받아오는 어노테이션 : @Autowired
    @Autowired
    private CartDao cartDao; // 객체 정의 ( null => 스프링 객체 )

//    나중에 에러 로그 추적을 위해 하나 만들어둠
    Logger logger = LoggerFactory.getLogger(this.getClass());

//    id로 회원 조회 하는 메소드
public Cart findById(Long id) {
    return cartDao.findById(id);
}

//    모든 회원 조회 서비스
    public Stream<Cart> findAll(CartCriteria criteria) {

//    todo:
        int totalCount = cartDao.selectTotalCart();

        criteria.setTotalItems(totalCount);
        criteria.setTotalPages(totalCount/criteria.getSize());
        Stream<Cart> resFileDB = cartDao.findAll(criteria).stream();

        return resFileDB;
    }


//    public Optional<Cart> save(Cart cart) {
//        long seqId = 0;
//
//        if(cart.getId() == null ){
//            cartDao.insertCart(cart);
//        }
//        else{
//            cartDao.updateCart(cart);
//        }
//
//        seqId = cart.getId();
//
//        return cartDao.findById2(seqId);
//    }

    //    장바구니 담기
    public Optional<Cart> save(Cart cart) {
//        db 시퀀스 번호 저장을 위한 변수
        long seqId = 0;

//        매개변수 customer 정보 출력
        logger.info("cart {}", cart);

        boolean checkCart = cartDao.checkCart(cart.getProductId());

//        customer.getId() 없으면 insert 문 호출( 새로운 회원 생성 )
        if(cart.getId() == null) {
            if(checkCart) {
                cartDao.deleteByProductId(cart.getProductId());
            }
            cartDao.insertCart(cart);
        }
        //                         있으면 update 문 호출
        else {
            cartDao.updateCart(cart);
        }

        seqId = cart.getId();
        logger.info("seqId {}", seqId);

        return Optional.ofNullable(cartDao.findById(seqId));
    }
//
public Optional<Cart> saveOrder(Cart cart) {
//        db 시퀀스 번호 저장을 위한 변수
    long seqId = 0;

//        매개변수 customer 정보 출력
    logger.info("cart {}", cart);

//        customer.getId() 없으면 insert 문 호출( 새로운 회원 생성 )
    if(cart.getId() == null) {
        cartDao.insertCartOrder(cart);
    }
    //                         있으면 update 문 호출
    else {
        cartDao.updateCart(cart);
    }

    seqId = cart.getId();
    logger.info("seqId {}", seqId);

    return Optional.ofNullable(cartDao.findById(seqId));
}
////    장바구니 있을 시
//    public Optional<Cart> Cartsave(Cart cart) {
////        db 시퀀스 번호 저장을 위한 변수
//        long seqId = 0;
//
////        매개변수 customer 정보 출력
//        logger.info("cart {}", cart);
//
////        customer.getId() 없으면 insert 문 호출( 새로운 회원 생성 )
//        if(cart.getProductId() == null) {
//            cartDao.insertCart(cart);
//        }
//        //                         있으면 update 문 호출
//        else {
//            cartDao.deleteById(seqId);
//        }
//
//        seqId = cart.getProductId();
//        logger.info("seqId {}", seqId);
//
//        return Optional.ofNullable(cartDao.findById(seqId));
//    }

    public Stream<Cart> findByCart(CartCriteria criteria) {

        int totalCount = cartDao.CartTotalCount(criteria.getId());

//		Tutorials 총 건수
        criteria.setTotalItems(totalCount);
        criteria.setTotalPages(totalCount/criteria.getSize());

        logger.info("criteria : {}",criteria);

        return cartDao.findByCart(criteria).stream();
    }


    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = cartDao.deleteById(id);

        return (queryResult >=1) ? true : false;
    }


    public boolean deleteAll() {
        int queryResult = 0;

//        delete / update / insert 문은 결과 실행 후
//        정상 실행되면 건수(삭제/수정/생성)가 결과로 리턴(반환)
        queryResult = cartDao.deleteAll();

        return (queryResult >=1) ? true : false;
    }
}











