package com.study.home_project.controller;

import com.study.home_project.annotation.ValidAspect;
import com.study.home_project.dto.AdminSigninRequestDto;
import com.study.home_project.dto.AdminSignupRequestDto;
import com.study.home_project.dto.OAuth2SignupRequestDto;
import com.study.home_project.service.AdminAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/auth")
public class AdminAuthController {
    @Autowired
    private AdminAuthService adminAuthService;

    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> adminSignup(@Valid @RequestBody AdminSignupRequestDto adminSignupRequestDto, BindingResult bindingResult) {
        adminAuthService.adminSignup(adminSignupRequestDto);
        return ResponseEntity.created(null).body(true);
    }
    @ValidAspect
    @PostMapping("/oauth2/signup")
    public ResponseEntity<?> oAuth2Signup(@Valid @RequestBody OAuth2SignupRequestDto OAuth2SignupRequestDto, BindingResult bindingResult) {
//        oAuth2Service.oAuth2Signup(oAuth2SignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> adminSignin(@RequestBody AdminSigninRequestDto adminSigninRequestDto) {
        return ResponseEntity.ok(adminAuthService.adminSignin(adminSigninRequestDto));
    }
}
