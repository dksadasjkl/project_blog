package com.study.home_project.dto;

import com.study.home_project.entity.Admin;
import com.study.home_project.entity.OAuth2;
import com.study.home_project.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class OAuth2SignupRequestDto {
    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "아이디는 영문자, 숫자 5 ~ 10자리 형식이어야 합니다.")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$",message = "비밀번호는 영문자, 숫자, 특수문자를 포함한 5  5 ~ 128자리 형식이어야 합니다.")
    private String password;
    @Pattern(regexp = "^[가-힇]{1,}$",message = "이름은 한글문자 형식이어야 합니다.")
    private String tradeName;
    @Email(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{1,3}$",message = "이메일 형식이어야 합니다.") //0314-2
    private String email;
    @NotBlank
    private String oauth2Name;
    @NotBlank
    private String providerName;

    public Admin toEntity(BCryptPasswordEncoder passwordEncoder) {
        return Admin.builder()
                .adminName(username)
                .adminPassword(passwordEncoder.encode(password))
                .tradeName(tradeName)
                .email(email)
                .build();
    }

    public OAuth2 toOAuth2(int adminId) {
        return OAuth2.builder()
                .oAuth2Name(oauth2Name)
                .adminId(adminId)
                .providerName(providerName)
                .build();
    }
}
