package com.example.taegyungsi.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * packageName : com.example.taegyungsi.security
 * fileName : JWTAuthenticatioFilter
 * author : gumin
 * date : 2022-06-20
 * description : JWT(JSON Web Token)가 유효한 토큰인지 인증하기 위한 Filter
 * (SpringBoot Security는 필터들로 인증을 진행함)
 * 유효하면 SecurityContextHolder에 저장 (인증객체로 저장) : setter
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20         gumin          최초 생성
 */
public class JWTAuthenticatioFilter extends GenericFilterBean {

    // 토근 관련 다양한 메소드를 사용하기 위한
    JWTTokenProvider jwtTokenProvider;

    // 기본 생성자 정의

    public JWTAuthenticatioFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // http 헤더에서 토큰 받아오기 (Vue(클라이언트, request)에서 토큰을 전송)
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // Vue에서 보낸 웹토큰이 유효한지 인증을 시작
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // if문 안에 들어오면 웹토큰이 유효함
            // 1. 웹토큰이 유효하면 JWT 토큰으로 DB에 유저정보를 조회함.
            // 2. 유저인증이 진행됨
            Authentication auth =
                    jwtTokenProvider.getAuthentication(token);
            // 관리를 위해서 아래 클래스 객체에 저장함
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        // 필터에 등록하기
        chain.doFilter(request, response);
    }
}
