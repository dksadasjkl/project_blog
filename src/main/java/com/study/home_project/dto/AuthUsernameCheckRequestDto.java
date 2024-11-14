package com.study.home_project.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class AuthUsernameCheckRequestDto {
    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "영문자, 숫자 5 ~ 10자리 형식이어야 합니다")
    private String username;
}
