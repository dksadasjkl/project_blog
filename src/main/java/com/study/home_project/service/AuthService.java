package com.study.home_project.service;

import com.study.home_project.dto.AuthSignupRequestDto;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public void signup(AuthSignupRequestDto authSignupRequestDto) {
        System.out.println(authSignupRequestDto);
    }

    public void signin(AuthSignupRequestDto authSignupRequestDto) {
        System.out.println(authSignupRequestDto);
    }
}
