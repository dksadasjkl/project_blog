package com.study.home_project.dto;

import lombok.Data;

@Data
public class AuthSignupRequestDto {
    private String username;
    private String password;
    private String email;
    private String profileImg;
}
