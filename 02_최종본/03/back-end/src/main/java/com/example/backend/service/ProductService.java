package com.example.backend.service;

import com.example.backend.dao.ProductDao;
import com.example.backend.model.Product;
import com.example.backend.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public int store(String title, String model, String description, long price, String category, int discount, int percent, String saleYn, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//    todo: filedb 수정 (title, content)
        Product Product = new Product(fileName, title, model, description,price, category, discount, percent, saleYn, file.getContentType(), file.getBytes());


        logger.info(" fileName {} " , fileName);
        logger.info(" title {} " , title);


        return productDao.save(Product);
    }

    public Product getFile(Long id) {
        return productDao.findById(id);
    }

    //  todo:
    public Stream<Product> getAllFiles(Criteria criteria) {

//    todo:
        int totalCount = productDao.selectTotalCount();

//		Tutorials 총 건수
        criteria.setTotalItems(totalCount);
        //	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
        criteria.setTotalPages(totalCount / criteria.getSize());
        //    list -> stream 객체로 변환
        Stream<Product> resFileDB = productDao.findAll(criteria).stream();

        return resFileDB;
    }

    // TODO: 타이틀 검색 기능 추가
    public Stream<Product> findByTitleContaining(Criteria criteria) {

        Optional<String> optionalCriteria
                = Optional.ofNullable(criteria.getTitle());
//    todo:
//    int totalCount = fileDBDao.selectTotalCount();

        int totalCount =
                productDao.selectTotalCount(optionalCriteria.orElse(""));

//		Tutorials 총 건수
        criteria.setTotalItems(totalCount);
        //	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
        criteria.setTotalPages(totalCount / criteria.getSize());
        //    list -> stream 객체로 변환

        if (criteria.getTitle() == null) {
            return productDao.findAll(criteria).stream();
        } else
            return productDao.findByTitleContaining(criteria).stream();
    }

    public Stream<Product> findByCategory(Criteria criteria) {

        Optional<String> optionalCriteria
                = Optional.ofNullable(criteria.getTitle());
//    todo:
//    int totalCount = fileDBDao.selectTotalCount();

        int totalCount =
                productDao.selectTotalCount(optionalCriteria.orElse(""));

//		Tutorials 총 건수
        criteria.setTotalItems(totalCount);
        //	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
        criteria.setTotalPages(totalCount / criteria.getSize());
        //    list -> stream 객체로 변환


        return productDao.findByCategory(criteria).stream();
    }


    public Stream<Product> findBySale(Criteria criteria) {

        Optional<String> optionalCriteria
                = Optional.ofNullable(criteria.getCatId());
//    todo:
//    int totalCount = fileDBDao.selectTotalCount();

        int totalCount =
                productDao.selectTotalCount(optionalCriteria.orElse(""));

//		Tutorials 총 건수
        criteria.setTotalItems(totalCount);
        //	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
        criteria.setTotalPages(totalCount / criteria.getSize());
        //    list -> stream 객체로 변환


        return productDao.findBySale(criteria).stream();
    }

    public Stream<Product> findNewProduct(Criteria criteria) {
        return productDao.findNewProduct(criteria).stream();
    }

    public Stream<Product> findRandomProduct(Criteria criteria) {
        return productDao.findRandomProduct(criteria).stream();
    }

    public Stream<Product> findRandomProductImg(Criteria criteria) {
        return productDao.findRandomProductImg(criteria).stream();
    }


    //TODO:업데이트 기능 추가
    public Optional<Product> update(Product product) {
//        db 시퀀스 번호 저장을 위한 변수
        long seqId = 0;

//        매개변수 customer 정보 출력
        logger.info("Product {}", product);

//        customer.getId() 없으면 insert 문 호출( 새로운 회원 생성 )
        if (product.getId() == null) {
            productDao.save(product);
        }
        //                         있으면 update 문 호출
        else {
            productDao.updateProduct(product);
        }

        seqId = product.getId();
        logger.info("seqId {}", seqId);

        return Optional.ofNullable(productDao.findById(seqId));
    }

    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = productDao.deleteProduct(id);

        return (queryResult >= 1) ? true : false;
    }


    public boolean deleteAll() {
        int queryResult = 0;

        queryResult = productDao.deleteAll();

        return (queryResult >= 1) ? true : false;
    }
}
