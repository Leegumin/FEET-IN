package com.example.backend.controller;

import com.example.backend.message.ResponseFile;
import com.example.backend.message.ResponseMessage;
import com.example.backend.model.Product;
import com.example.backend.paging.ProductCriteria;
import com.example.backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/uploadProduct")
    public ResponseEntity<ResponseMessage> uploadFile(
            @RequestParam("category") String category,
            @RequestParam("title") String title,
            @RequestParam("model") String model,
            @RequestParam("description") String description,
            @RequestParam("price") long price,
            @RequestParam("discount") int discount,
            @RequestParam("percent") int percent,
            @RequestParam("saleYn") String saleYn,
            @RequestParam("file") MultipartFile file
    ) {
        String message = "";

        logger.info("title {} : ", title);
        logger.info("file {} : ", file);

        try {
            productService.store(title, model, description,price, category, discount, percent, saleYn, file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<ResponseFile>> getListFiles(ProductCriteria productCriteria) {
        List<ResponseFile> files = productService.findByTitleContaining(productCriteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getCategory(),
                    dbFile.getDescription(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    dbFile.getPercent(),
                    dbFile.getSaleYn(),
                    dbFile.getInsertTime(),
                    productCriteria.getPage(),
                    productCriteria.getTotalItems(),
                    productCriteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/category/")
    public ResponseEntity<List<ResponseFile>> getListCategory(ProductCriteria productCriteria) {
        List<ResponseFile> files = productService.findByCategory(productCriteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getCategory(),
                    dbFile.getDescription(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    dbFile.getPercent(),
                    dbFile.getSaleYn(),
                    dbFile.getInsertTime(),
                    productCriteria.getPage(),
                    productCriteria.getTotalItems(),
                    productCriteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/sales")
    public ResponseEntity<List<ResponseFile>> getListSale(ProductCriteria productCriteria) {
        List<ResponseFile> files = productService.findBySale(productCriteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getCategory(),
                    dbFile.getDescription(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    dbFile.getPercent(),
                    dbFile.getSaleYn(),
                    dbFile.getInsertTime(),
                    productCriteria.getPage(),
                    productCriteria.getTotalItems(),
                    productCriteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/new")
    public ResponseEntity<List<ResponseFile>> getNewProduct(ProductCriteria productCriteria) {
        List<ResponseFile> files = productService.findNewProduct(productCriteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getCategory(),
                    dbFile.getDescription(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    dbFile.getPercent(),
                    dbFile.getSaleYn(),
                    dbFile.getInsertTime(),
                    productCriteria.getPage(),
                    productCriteria.getTotalItems(),
                    productCriteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/random")
    public ResponseEntity<List<ResponseFile>> getRandomProduct(ProductCriteria productCriteria) {
        List<ResponseFile> files = productService.findRandomProduct(productCriteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFile(
//        todo: dbFile getTitle(), getContent(), criteria.getPage(),criteria.getTotalItems(),
//               criteria.getTotalPages(),
//                    todo: dbFile.getId(), 추가
                    dbFile.getId(),
                    dbFile.getName(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getCategory(),
                    dbFile.getDescription(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    dbFile.getPercent(),
                    dbFile.getSaleYn(),
                    dbFile.getInsertTime(),
                    productCriteria.getPage(),
                    productCriteria.getTotalItems(),
                    productCriteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/randomImg")
    public ResponseEntity<List<ResponseFile>> getRandomProductImg(ProductCriteria productCriteria) {
        List<ResponseFile> files = productService.findRandomProductImg(productCriteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getCategory(),
                    dbFile.getDescription(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    dbFile.getPercent(),
                    dbFile.getSaleYn(),
                    dbFile.getInsertTime(),
                    productCriteria.getPage(),
                    productCriteria.getTotalItems(),
                    productCriteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    @GetMapping("/best")
    public ResponseEntity<List<ResponseFile>> getBestProduct(ProductCriteria productCriteria) {
        List<ResponseFile> files = productService.findByBestProduct(productCriteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    dbFile.getTitle(),
                    dbFile.getModel(),
                    dbFile.getCategory(),
                    dbFile.getDescription(),
                    dbFile.getPrice(),
                    dbFile.getDiscount(),
                    dbFile.getPercent(),
                    dbFile.getSaleYn(),
                    dbFile.getInsertTime(),
                    productCriteria.getPage(),
                    productCriteria.getTotalItems(),
                    productCriteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable long id) {
        Product product = productService.getFile(id);

        return ResponseEntity.ok()
//           Todo : attachment: => attachment;
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + product.getName() + "\"")
                .body(product.getData());
    }
//
@GetMapping("/productDetail")
public ResponseEntity<ResponseFile> findById(@RequestParam("id") long id) {
    logger.info("request param id : {}", id);
    Product dbFile = productService.getFile(id);

    String fileDownloadUri = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/api/products/")
            .path(String.valueOf(dbFile.getId()))
            .toUriString();

    ResponseFile resFile =  new ResponseFile(
            dbFile.getId(),
            dbFile.getName(),
            dbFile.getTitle(),
            dbFile.getModel(),
            dbFile.getCategory(),
            dbFile.getDescription(),
            dbFile.getPrice(),
            dbFile.getDiscount(),
            dbFile.getPercent(),
            dbFile.getSaleYn(),
            dbFile.getInsertTime(),
            0,
            0,
            0,
            fileDownloadUri,
            dbFile.getType(),
            dbFile.getData().length);

    return ResponseEntity.status(HttpStatus.OK).body(resFile);
}

@PutMapping("/product/{id}")
public ResponseEntity<Object> updateProduct(
        @PathVariable("id") Long id,
        @RequestBody Product product
) {
    try {
        product.setId(id);
        Product saveProduct = productService.update(product).get();

        return new ResponseEntity<Object>(saveProduct, HttpStatus.OK);

    } catch (Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
}

@PutMapping("/products/deletion/{id}")
public ResponseEntity<HttpStatus> deleteProduct(
        @PathVariable("id") Long id
){
        logger.info("id", id);
    try {
        productService.deleteById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    } catch (Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
}
