package com.example.taegyungsi.security;

import com.example.taegyungsi.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * packageName : com.example.taegyungsi.security
 * fileName : JWTTokenProvider
 * author : gumin
 * date : 2022-06-20
 * description : JWT 웹토큰을 생성, 인증, 권한부여, 유효성검사, PK추출 등
 * 다양한 기능을 제공하는 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20         gumin          최초 생성
 */
// RequiredArgsConstructor : Rombok 어노테이션, final이나 @NotNull 붙은 멤버변수를 가지고 생성자를 만듬
@RequiredArgsConstructor
// Component : springBoot의 객체로 생성하는 어노테이션
@Component
public class JWTTokenProvider {

    // Logger 추가
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // JWT 웹토큰 -> 서버로 전송 -> 서버에서 secretKey 거친 후 인증됨
    // spring.jwt.secret : application.properties에 만들어둔 인증키임
    @Value("spring.jwt.secret")
    private String secretKey; // JWT 인증키를 담을 변수 (spring.jwt.secret)

    // 만료시간 변수 (밀리세컨드) : 1시간만 토큰이 유효하게 만듬
    private long tokenValidMilisecond = 60 * 60 * 1000L;

    // DB에 접속해서 User 정보를 가져오는 서비스
    private final CustomUserDetailService customUserDetailService;

    // PostConstruct : @Autowired 이후에 초기화 진행하는 생성자
    // 역할 : Base64 인코딩으로 변환시킴(secretKey)
    // Base64 : binary 데이터를 text로 변환 (목적 : 보안, 표준화 등등)
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT 토큰 생성 메서드
    public String createToken(String userId, List<String> roles) {

        // JWT 내용(payload)부분의 sub(제목)에 id를 저장
        Claims claims = Jwts.claims().setSubject(userId);
        // 역할 ( ROLE_USER, ROLE_ADMIN 등등 )
        claims.put("roles", roles);
        // 현재 날짜
        Date now = new Date();

        // 실제 웹토큰 생성하는 부분
        return Jwts.builder()
                   .setClaims(claims) // 클라임 데이터
                   .setIssuedAt(now) // 토큰 발행 날짜
                   .setExpiration(new Date(now.getTime() + tokenValidMilisecond)) // 토큰 만료 시간(1시간)
                   .signWith(SignatureAlgorithm.HS256, secretKey) // secretKey에 암호화 알고리즘 적용
                   .compact(); // 최종 생성자 빌드
    }

    // JWT 토큰에서 id를 추출하는 메소드
    public String getUserId(String token) {
        return Jwts.parser()
                   // secretKey 안에 token도 포함돼 있음. 같이 해석됨
                   .setSigningKey(secretKey)
                   .parseClaimsJws(token) // 해석한 토큰을 문자열로 변환
                   .getBody()
                   .getSubject(); // 웹토큰에서 회원정보 id가 추출됨
    }

    // JWT 토큰으로 추출한 id로 인증 정보를 조회하는 메소드
    // Authentication == User == UserDetails : 인증된 회원정보를 가지는 객체
    public Authentication getAuthentication(String token) {
        // 토큰(인증키)으로 회원정보 얻기
        UserDetails userDetails =
                customUserDetailService.loadUserByUsername(this.getUserId(token));

        // UsernamePasswordAuthenticationToken : 호출시 인증된 객체가 나옴
        // 인증된 객체는 Authentication에 등록됨
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    // 유틸리티성 메소드 : html 등 헤더정보 확인 메소드
    //           확인 : 토큰정보 확인 ("Authorization : 'Bearer'")
    public String resolveToken(HttpServletRequest req) {
        // return req.getHeader("X-AUTH-TOKEN"); 도 가능?
        return req.getHeader("Authorization : 'Bearer'");
    }

    // 유틸리티성 메소드
    // JWT 웹토큰의 유효성 + 만료일자 확인하는 메소드
    // 만료시간 안에 있으면 : true -> 만료시간보다 new Date가 빨라서 false, !가 붙어서 true
    // 만료시간 지나면 : true -> 만료시간보다 new Date가 빨라서 false, !가 붙어서 true
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser()
                                     .setSigningKey(secretKey)
                                     .parseClaimsJws(jwtToken);
            // 예) Date date = claims.getBody().getEpiration();
            // 날짜관련 클래스(메소드 : date.equal(new Date())) -> 시간이 같으면
            // 날짜관련 클래스(메소드 : date.before(new Date()) -> date > new Date() : true, date가 시간이 더 빠르면
            // 날짜관련 클래스(메소드 : date.after(new Date()) -> date < new Date() : true, date가 시간이 더 느리면
            // ex) date(14:20).before(new Date()(14:30)):true
            return !claims.getBody().getExpiration().before(new Date());
        }
        catch (Exception e) {
            // e.getMessage();
            logger.info("validateToken Error {}", e.getMessage());
            return false;
        }
    }
}
