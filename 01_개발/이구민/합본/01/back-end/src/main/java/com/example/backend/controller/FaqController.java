package com.example.backend.controller;

import com.example.backend.service.FaqServiceImpl;
import com.example.backend.model.Faq;
import com.example.backend.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class FaqController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FaqServiceImpl faqService;

    @PostMapping("/faqs")
    public ResponseEntity<Object> createFaq(@RequestBody Faq faq){
        Faq savedFaq = faqService.save(faq).get();

        try{
            return new ResponseEntity<Object>(savedFaq, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    //상세페이지
    @GetMapping("/faqs/detail/{id}")
    public ResponseEntity<Object>detailFaq(@PathVariable("id") long id){
        Faq detailFaq = faqService.detailFaq(id).get();

        try {
            return new ResponseEntity<Object>(detailFaq, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/faqs/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id")Long id){

        Faq faq = faqService.findById(id).get();

        try{
            if(faq != null){
                return new ResponseEntity<Object>(faq, HttpStatus.OK);
            }else{
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/faqs")
    public ResponseEntity<Map<String, Object>> getAllTitlePage(Criteria criteria){



        try{
            List<Faq> faqs = faqService.findByTitleContaining(criteria);
            if(faqs.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("faqs", faqs);
            response.put("currentPage", criteria.getPage());
            response.put("totalItems", criteria.getTotalItems());
            response.put("totalPage", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/faqs/{id}")
    public ResponseEntity<Object> updateFaq( @RequestBody Faq faq, @PathVariable("id")Long id){
        try{
            faq.setId(id);
            Faq savedFaq = faqService.save(faq).get();
            return new ResponseEntity<Object>(savedFaq, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/faqs/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteFaq(
            @PathVariable("id") Long id) {
        try{
            faqService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }

    }

}
