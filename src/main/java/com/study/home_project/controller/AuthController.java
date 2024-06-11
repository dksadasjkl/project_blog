package com.study.home_project.controller;

import com.study.home_project.dto.AuthSignupRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public ResponseEntity<?> signup(@RequestBody AuthSignupRequestDto authSignupRequestDto) {
        System.out.println(authSignupRequestDto);
        return ResponseEntity.created(null).body(true);
    }
}
