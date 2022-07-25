package com.example.backend.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@ToString
// *스프링에서 요청이 온 데이터 유효성 검사돌림
public class SignupRequest {
// *@NotBlank 쓸라믄 null 값으로 들어오는거 단 하나도 없어야함 안그러면 회원정보수정에서 뻑남
    
    //  사용자 아이디
    // @NotBlank
    @Size(max = 20)
    private String username;

    //  비밀번호
    // @NotBlank
    @Size(max = 120)
    private String password;

    //  새비밀번호
    // @NotBlank
    @Size(max = 120)
    private String newPassword;

    // 이름
    // @NotBlank
    @Size(max = 50)
    private String name;

    /*// 생일
    @Size(max = 50)
    private String birth;*/

    //  이메일
    // @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    // 주소
    // @NotBlank
    @Size(max = 120)
    private String address;

    // 핸드폰번호
    // @NotBlank
    @Size(max = 20)
    private String phone;

    // 탈퇴여부
    private String deleteYn;

    // 가입일자
    private String insertTime;

    // 회원정보수정 일자
    private String updateTime;

    // 회원 탈퇴 일자
    private String deleteTime;

    //  private Set<String> roles;
    private String role;


}
