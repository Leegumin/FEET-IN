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

    public int store(String title, String model, String description, long price, String category, int discount, int percent, String saleYn, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Product Product = new Product(fileName, title, model, description,price, category, discount, percent, saleYn, file.getContentType(), file.getBytes());


        logger.info(" fileName {} " , fileName);
        logger.info(" title {} " , title);


        return productDao.save(Product);
    }

    public Product getFile(Long id) {
        return productDao.findById(id);
    }

    public Stream<Product> findByTitleContaining(ProductCriteria productCriteria) {

        Optional<String> optionalCriteria
                = Optional.ofNullable(productCriteria.getTitle());

        int totalCount =
                productDao.selectTotalCount(optionalCriteria.orElse(""));

        productCriteria.setTotalItems(totalCount);
        productCriteria.setTotalPages(totalCount / productCriteria.getSize());

        if (productCriteria.getTitle() == null) {
            return productDao.findAll(productCriteria).stream();
        } else
            return productDao.findByTitleContaining(productCriteria).stream();
    }

    public Stream<Product> findByCategory(ProductCriteria productCriteria) {

        Optional<String> optionalCriteria
                = Optional.ofNullable(productCriteria.getTitle());

        int totalCount =
                productDao.selectCategoryCount(optionalCriteria.orElse(""));

        productCriteria.setTotalItems(totalCount);
        productCriteria.setTotalPages(totalCount / productCriteria.getSize());

        return productDao.findByCategory(productCriteria).stream();
    }


    public Stream<Product> findBySale(ProductCriteria productCriteria) {

        productCriteria.setSaleYn("Y");

        int totalCount = productDao.selectSaleCount(productCriteria.getSaleYn());

        productCriteria.setTotalItems(totalCount);
        productCriteria.setTotalPages(totalCount / productCriteria.getSize());

        return productDao.findBySale(productCriteria).stream();
    }

    public Stream<Product> findByBestProduct(ProductCriteria productCriteria) {
        return productDao.findByBestProduct(productCriteria).stream();
    }
    public Stream<Product> findNewProduct(ProductCriteria productCriteria) {
        return productDao.findNewProduct(productCriteria).stream();
    }

    public Stream<Product> findRandomProduct(ProductCriteria productCriteria) {
        return productDao.findRandomProduct(productCriteria).stream();
    }

    public Stream<Product> findRandomProductImg(ProductCriteria productCriteria) {
        return productDao.findRandomProductImg(productCriteria).stream();
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
