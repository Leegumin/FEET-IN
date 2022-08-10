package com.example.backend.service;

import com.example.backend.dao.ProductDao;
import com.example.backend.model.Product;
import com.example.backend.paging.ProductCriteria;
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

    // 상품 등록
    public int store(String title, String model, String description, long price, String category, int discount, int percent, String saleYn, MultipartFile file) throws IOException {

        // *StringUtils.cleanPath :  파일 경로 "\"(역슬레시)를 "/"슬레시로 바꿔서 출력
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Product Product = new Product(fileName, title, model, description,price, category, discount, percent, saleYn, file.getContentType(), file.getBytes());

        logger.info(" fileName {} " , fileName);
        logger.info(" title {} " , title);

        return productDao.save(Product);
    }

    // 상품 고유키(id)로 조회
    public Product getFile(Long id) {
        return productDao.findById(id);
    }

    // 상품 조회
    public Stream<Product> findByTitleContaining(ProductCriteria productCriteria) {

        // *Optional.ofNullable : null값을 허용함, null이 들어오면 비어있는 객체를 반환
        Optional<String> optionalCriteria
                = Optional.ofNullable(productCriteria.getTitle());

        // 상품 총 개수를 카운트하는 dao 실행
        // *optionalCriteria.orElse : optionalCriteria가 비어있다면 "" 반환
        int totalCount =
                productDao.selectTotalCount(optionalCriteria.orElse(""));

        // 카운트된 상품 총 개수를 dto에 저장
        productCriteria.setTotalItems(totalCount);
        // 카운트된 상품 총 개수와 페이지당 출력할 개수(size)를 연산하여 총 페이지수를 구함
        productCriteria.setTotalPages(totalCount / productCriteria.getSize());

        // 상품 이름이 null일 경우(없을 경우) 전체 조회
        if (productCriteria.getTitle() == null) {
            return productDao.findAll(productCriteria).stream();
        // 상품 이름이 있을 경우 상품이름으로 조회
        } else
            return productDao.findByTitleContaining(productCriteria).stream();
    }

    // 상품 카테고리로 조회
    public Stream<Product> findByCategory(ProductCriteria productCriteria) {

        Optional<String> optionalCriteria
                = Optional.ofNullable(productCriteria.getTitle());

        int totalCount =
                productDao.selectCategoryCount(optionalCriteria.orElse(""));

        productCriteria.setTotalItems(totalCount);
        productCriteria.setTotalPages(totalCount / productCriteria.getSize());

        return productDao.findByCategory(productCriteria).stream();
    }


    // 세일 상품 조회
    public Stream<Product> findBySale(ProductCriteria productCriteria) {

        productCriteria.setSaleYn("Y");

        int totalCount = productDao.selectSaleCount(productCriteria.getSaleYn());

        productCriteria.setTotalItems(totalCount);
        productCriteria.setTotalPages(totalCount / productCriteria.getSize());

        return productDao.findBySale(productCriteria).stream();
    }

    // 베스트 상품 조회
    public Stream<Product> findByBestProduct(ProductCriteria productCriteria) {
        return productDao.findByBestProduct(productCriteria).stream();
    }
    // 최근 상품 조회
    public Stream<Product> findNewProduct(ProductCriteria productCriteria) {
        return productDao.findNewProduct(productCriteria).stream();
    }

    // 랜덤 상품 조회
    public Stream<Product> findRandomProduct(ProductCriteria productCriteria) {
        return productDao.findRandomProduct(productCriteria).stream();
    }

    // 랜덤 상품 이미지
    public Stream<Product> findRandomProductImg(ProductCriteria productCriteria) {
        return productDao.findRandomProductImg(productCriteria).stream();
    }


    // 상품 수정
    public Optional<Product> update(Product product) {
        //  db 시퀀스 번호 저장을 위한 변수
        long seqId = 0;

       // 매개변수 customer 정보 출력
        logger.info("Product {}", product);

        // customer.getId() 없으면 insert 문 호출 ( 새로운 상품 등록 )
        if (product.getId() == null) {
            productDao.save(product);
        }
        // 있으면 update 문 호출
        else {
            productDao.updateProduct(product);
        }

        seqId = product.getId();
        logger.info("seqId {}", seqId);

        return Optional.ofNullable(productDao.findById(seqId));
    }

    // 상품 삭제
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = productDao.deleteProduct(id);

        return queryResult >= 1;
    }

    // 상품 전체 삭제
    public boolean deleteAll() {
        int queryResult = 0;

        queryResult = productDao.deleteAll();

        return queryResult >= 1;
    }
}
