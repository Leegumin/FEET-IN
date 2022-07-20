package com.example.backend.controller;

import com.example.backend.message.ResponseFile;
import com.example.backend.message.ResponseMessage;
import com.example.backend.model.Product;
import com.example.backend.paging.Criteria;
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

    //  Todo:
    @GetMapping("/products")
    public ResponseEntity<List<ResponseFile>> getListFiles(Criteria criteria) {
        List<ResponseFile> files = productService.findByTitleContaining(criteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
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
                    criteria.getPage(),
                    criteria.getTotalItems(),
                    criteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/category/")
    public ResponseEntity<List<ResponseFile>> getListCategory(Criteria criteria) {
        List<ResponseFile> files = productService.findByCategory(criteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
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
                    criteria.getPage(),
                    criteria.getTotalItems(),
                    criteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/sales")
    public ResponseEntity<List<ResponseFile>> getListSale(Criteria criteria) {
        List<ResponseFile> files = productService.findBySale(criteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
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
                    criteria.getPage(),
                    criteria.getTotalItems(),
                    criteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/new")
    public ResponseEntity<List<ResponseFile>> getNewProduct(Criteria criteria) {
        List<ResponseFile> files = productService.findNewProduct(criteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
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
                    criteria.getPage(),
                    criteria.getTotalItems(),
                    criteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/random")
    public ResponseEntity<List<ResponseFile>> getRandomProduct(Criteria criteria) {
        List<ResponseFile> files = productService.findRandomProduct(criteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
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
                    criteria.getPage(),
                    criteria.getTotalItems(),
                    criteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/randomImg")
    public ResponseEntity<List<ResponseFile>> getRandomProductImg(Criteria criteria) {
        List<ResponseFile> files = productService.findRandomProductImg(criteria).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/products/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
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
                    criteria.getPage(),
                    criteria.getTotalItems(),
                    criteria.getTotalPages(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


// todo : .path("/api/files/").path(dbFile.getId())
//    이미지 다운로드 위치 : 아래 평션이 실행됨
//    아래 지우면 안됨
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
//        todo: id 확인
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

//    TODO:업데이트 추가
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
//            Vue 에러메세지 전송
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
}

//   TODO:삭제 추가
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
