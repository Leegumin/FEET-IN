package com.example.backend.controller;

import com.example.backend.model.*;
import com.example.backend.payload.request.LoginRequest;
import com.example.backend.payload.request.SignupRequest;
import com.example.backend.payload.response.JwtResponse;
import com.example.backend.payload.response.MessageResponse;
import com.example.backend.security.jwt.JwtUtils;
import com.example.backend.security.services.UserDetailsImpl;
import com.example.backend.service.RoleServiceImpl;
import com.example.backend.service.SendEmailServiceImp;
import com.example.backend.service.UserServiceImpl;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SendEmailServiceImp sendEmailService;

    // 로그인 메소드
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid
                                              @RequestBody
                                              LoginRequest loginRequest) {

        //  사용자 이름, 비밀번호 인증
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        //  SecurityContext 를 사용하여 업데이트 Authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //  JWT 토큰 생성
        String jwt = jwtUtils.generateJwtToken(authentication);

        //  유저 상세 정보( UserDetails ) 가져오기
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //  응답 포함 JWT 및 UwerDetails 데이터
        List<String> role = userDetails.getAuthorities()
                                       .stream()
                                       .map(item -> item.getAuthority())
                                       .collect(Collectors.toList());

        //    테스트
        /*roles.add("ROLE_USER");*/


        logger.info("loginRequest authenticateUser {} ", role);

        return ResponseEntity.ok(
                new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getName(),
                                userDetails.getAddress(), userDetails.getEmail(), role, userDetails.getPhone(),
                                userDetails.getDeleteYn(), userDetails.getInsertTime(), userDetails.getUpdateTime(),
                                userDetails.getDeleteTime()));
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid
                                          @RequestBody
                                          SignupRequest signUpRequest) {

        logger.info("signUpRequest {} ", signUpRequest);

        if (userService.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                             encoder.encode(signUpRequest.getPassword()), signUpRequest.getName(),
                             signUpRequest.getAddress(), signUpRequest.getPhone(), signUpRequest.getDeleteYn(),
                             signUpRequest.getInsertTime(), signUpRequest.getUpdateTime(),
                             signUpRequest.getDeleteTime());

//    Set<String> strRoles = signUpRequest.getRoles();
        String strRoles = signUpRequest.getRole();
//    Set<Role> roles = new HashSet<>();
        Set<String> roles = new HashSet<>();

        // * 클라이언트에서 strRoles 즉, 요청한 권한이 없을 때
        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.ROLE_USER)
                                       .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole.getName().name());
//      user 객체 저장
            user.setRole(userRole.getName().name());
        }
        // * 클라이언트에서 strRoles 즉, 요청한 권한이 있을 때
        else {
//      strRoles.forEach(role -> {
            switch (strRoles) {
                case "ROLE_ADMIN":
                    Role adminRole = roleService.findByName(ERole.ROLE_ADMIN)
                                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole.getName().name());
//      user 객체 저장
                    user.setRole(adminRole.getName().name());
                    break;
                /*case "ROLE_MODERATOR":
                    Role modRole = roleService.findByName(ERole.ROLE_MODERATOR)
                                              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole.getName().name());
//      user 객체 저장
                    user.setRole(modRole.getName().name());
                    break;*/
                default:
                    Role userRole = roleService.findByName(ERole.ROLE_USER)
                                               .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole.getName().name());
                    //      user 객체 저장
                    user.setRole(userRole.getName().name());
            }
//      });
        }

        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    // 회원 탈퇴(update)
    @PutMapping("/user-deletion/{id}")
    public ResponseEntity<HttpStatus> deleteUser(
            @PathVariable("id")
            Long id) {

        boolean bSuccess = userService.deleteUser(id);

        logger.info("bSuccess {}", bSuccess);

        try {
//			boolean bSuccess = tutorialService.deleteById(id);

            if (bSuccess) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*    // 회원가입 수정1
    @PutMapping("/user-update")
    public ResponseEntity<?> updateUser(@Valid
                                        @RequestBody
                                        SignupRequest signUpRequest) {

        logger.info("signUpRequest {} ", signUpRequest);
        Optional<User> user = userService.findByUserName(signUpRequest.getUsername());

        logger.info("USER {} ", user);
        // user가 존재하지 않을 때
        // *!user.isPresent() => user == null
        if (!user.isPresent()) {
            return ResponseEntity.badRequest()
                                 .body(new MessageResponse("Error : User Is Not Exist"));
        }

        // 현재 비밀번호가 불일치 할 때
        if (!encoder.matches(signUpRequest.getPassword(), user.get().getPassword())) {
            return ResponseEntity.badRequest()
                                 .body(new MessageResponse("Error : Current Password Different!"));
        }

        // 현재 비밀번호가 일치할 때
        try {
            // 업데이트 유저 정보 생성
            User updateUser = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                                       encoder.encode(signUpRequest.getPassword()), signUpRequest.getName(),
                                       signUpRequest.getAddress(), signUpRequest.getPhone(),
                                       signUpRequest.getDeleteYn(), signUpRequest.getInsertTime(),
                                       signUpRequest.getUpdateTime(),
                                       signUpRequest.getDeleteTime());

            // 새 비밀번호가 있으면 새 비밀번호로 변경
            if (signUpRequest.getNewPassword() != null) {
                updateUser.setPassword(encoder.encode(signUpRequest.getNewPassword()));
            }

            logger.info("updateUser {} ", updateUser);

            // 업데이트 실행
            userService.updateUser(updateUser);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .body(new MessageResponse("Error : User registered Failure!"));
        }
    }
}*/

    // 회원가입 수정2
    @PutMapping("/user-update")
    public ResponseEntity<?> updateUser(@Valid
                                        @RequestBody
                                        SignupRequest signUpRequest) {
        logger.info("signUpRequest {} ", signUpRequest);
        Optional<User> user = userService.findByUserName(signUpRequest.getUsername());

        logger.info("USER {} ", user);
        // user가 존재하지 않을 때
        // *!user.isPresent() => user == null
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error : User Is Not Exist"));
        }

        // 현재 비밀번호가 불일치 할 때
        if (!encoder.matches(signUpRequest.getPassword(), user.get().getPassword())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error : Current Password Different!"));
        }

        // 현재 비밀번호가 일치할 때
        try {
            // 새 비밀번호가 있으면 새 비밀번호로 변경
            // *새 비밀번호 값이 공백으로 들어와서 .isEmpty()를 썼음
            if (!signUpRequest.getNewPassword().isEmpty()) {
                logger.info("test {} ", signUpRequest.getNewPassword());
                user.get().setPassword(encoder.encode(signUpRequest.getNewPassword()));
            }
            // 업데이트 정보 재입력
            user.get().setEmail(signUpRequest.getEmail());
            user.get().setAddress(signUpRequest.getAddress());
            user.get().setPhone(signUpRequest.getPhone());

            logger.info("updateUser {} ", user);

            // 업데이트 실행
            userService.updateUser(user.get());
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error : User registered Failure!"));
        }
    }

    // 이메일 인증
    /*@PostMapping("/mail")
    public ResponseEntity<?> sendEmail(
            @RequestBody
            SendEmailDto sendEmailDto, HttpServletResponse response) {
        logger.info("mail이 호출되었습니다.");
        logger.info("sendEmailDto:{}", sendEmailDto);

        try {
            User userID = sendEmailService.findID(sendEmailDto.getName(), sendEmailDto.getEmail());
            logger.info("userID : {}", userID);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out;
            // SimpleEmail 기본정보 입력
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.naver.com");
            email.setSmtpPort(587);
            email.setAuthentication("littlegbear", "K1GLJB4ENX3M");

            // 보안연결 SSL, TLS 사용 설정
            email.setSSLOnConnect(true);
            email.setStartTLSEnabled(true);

            logger.info("메일 대기중");
            // 보내는 사람 설정 (SMTP 서비스 로그인 계정 아이디와 동일하게 해야함에 주의!)
            email.setFrom("littlegbear@naver.com", "littlegbear", "utf-8");
            // 받는 사람 설정
            *//*email.addTo(userID.get().getEmail(), userID.get().getName(), "utf-8");*//*
            email.addTo("littlegbear@naver.com", userID.getName(), "utf-8");
            // 제목 설정
            email.setSubject("테스트입니다.");

            String ePw = sendEmailService.createKey();
            email.setMsg("안녕하세요" + userID.getName() + "님"
                         + "\n아래 코드를 회원가입 창으로 돌아가 입력해주세요"
                         + "\nCODE : " + ePw);
            // 메일 전송하기
            try {
                out = response.getWriter();
                email.send();
                out.println("<script>alert('메일을 보냈습니다'); history.back();</script>");
                return new ResponseEntity<>(ePw, HttpStatus.OK);
            }
            catch (IOException e) {
                logger.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (EmailException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @PostMapping("/user-findid")
    public ResponseEntity<?> sendEmail(
            @RequestBody
            SendEmailDto sendEmailDto) {
        logger.info("mail이 호출되었습니다.");

        try {
            //        User user = sendEmailService.findID(sendEmailDto.getName(), sendEmailDto.getEmail());
            User user = sendEmailService.findID(sendEmailDto);
            logger.info("user : {}", user);

            sendEmailDto.setEmail(user.getEmail());
            sendEmailDto.setName(user.getName());

            // 랜덤 코드
            sendEmailDto.setCode(sendEmailService.createKey());
            sendEmailDto.setUsername(user.getUsername());

            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.naver.com");
            email.setSmtpPort(587);
            email.setAuthentication("littlegbear", "K1GLJB4ENX3M");

            // 보안연결 SSL, TLS 사용 설정
            email.setSSLOnConnect(true);
            email.setStartTLSEnabled(true);

            logger.info("mail을 보내는 중입니다.");
            // 보내는 사람 설정 (SMTP 서비스 로그인 계정 아이디와 동일하게 해야함에 주의!)
            email.setFrom("littlegbear@naver.com", "관리자", "utf-8");
            // 받는 사람 설정
//            email1.addTo("littlegbear@naver.com", "이름", "utf-8");
            email.addTo(sendEmailDto.getEmail(), sendEmailDto.getName(), "utf-8");
            // 제목 설정
            email.setSubject("제목입니다");

            email.setMsg("안녕하세요" + sendEmailDto.getName() + "님"
                         + "\n아래 코드를 회원가입 창으로 돌아가 입력해주세요"
                         + "\nCODE : " + sendEmailDto.getCode());
            // 메일 전송하기
            email.send();

            HttpHeaders messageResponse = new HttpHeaders();
            messageResponse.set("Response Message","Email Send successfully!");


            // 객체로 보내는 형태 찾아보기
//            return new ResponseEntity<>(ePw, messageResponse, HttpStatus.OK);
            return new ResponseEntity<>(sendEmailDto, messageResponse, HttpStatus.OK);
        }
        catch (Exception e) {
            logger.info("sendEmail : {}", e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse("Error : Email Send Failure!"));
        }
    }
}

