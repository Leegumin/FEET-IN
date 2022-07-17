package com.example.backend.controller;

import com.example.backend.model.Faq;
import com.example.backend.paging.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/auth")
public class AdminController {
//  공개 엑세스용
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

//  사용자용 ROLE_USER 또는 ROLE_MODERATOR 또는 ROLE_ADMIN
  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

////  ROLE_MODERATOR
//  @GetMapping("/mod")
//  @PreAuthorize("hasRole('MODERATOR')")
//  public String moderatorAccess() {
//    return "Moderator Board.";
//  }

//  ROLE_ADMIN
  @GetMapping("/admin-page")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    
    return "Admin Board.";
  }

  /*@GetMapping("/faqs")
  // @PreAuthorize("hasRole('ADMIN')") : hasRole의 ADMIN은 원래 ROLE_ADMIN, 권한에서 ROLE_ADMIN으로 설정해주면 ADMIN으로 사용가능함
  // ROLE_USER -> USER만 써주면 된다는 소리
  // 해당 권한이 없는 사람이 접속하면 요청을 묵살해버림 -> 오류남
  @PreAuthorize("hasRole('ADMIN')")
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
  }*/
  
}