package com.study.home_project.dto;

import com.study.home_project.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AuthSignupRequestDto {
    @NotBlank(message = "전화번호를 입력하세요")
    private String phoneNumber;


    public User toEntity() {
        return User.builder()
                .phoneNumber(phoneNumber)
                .build();
    }
}
