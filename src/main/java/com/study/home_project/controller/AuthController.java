package com.study.home_project.controller;

import com.study.home_project.dto.AuthSignupRequestDto;
import com.study.home_project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@Valid @RequestBody AuthSignupRequestDto authSignupRequestDto) {
        System.out.println(authSignupRequestDto);
        authService.signup(authSignupRequestDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signin(@RequestBody AuthSignupRequestDto authSignupRequestDto) {
        System.out.println(authSignupRequestDto);
        authService.signup(authSignupRequestDto);
        return ResponseEntity.created(null).body(true);
    }
}
