package com.study.home_project.controller;

import com.study.home_project.annotation.ValidAspect;
import com.study.home_project.dto.request.AdminSigninRequestDto;
import com.study.home_project.dto.request.AdminSignupRequestDto;
import com.study.home_project.dto.request.OAuth2MergeRequestDto;
import com.study.home_project.dto.request.OAuth2SignupRequestDto;
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
@RequestMapping("/admin")
public class AdminAuthController {
    @Autowired
    private AdminAuthService adminAuthService;


    @ValidAspect
    @PostMapping("/auth/signup")
    public ResponseEntity<?> adminSignup(@Valid @RequestBody AdminSignupRequestDto adminSignupRequestDto, BindingResult bindingResult) {
        System.out.println(adminSignupRequestDto);
        adminAuthService.adminSignup(adminSignupRequestDto);
        return ResponseEntity.created(null).body(true);
    }
    @PostMapping("/auth/signin")
    public ResponseEntity<?> adminSignin(@RequestBody AdminSigninRequestDto adminSigninRequestDto) {
        return ResponseEntity.ok(adminAuthService.adminSignin(adminSigninRequestDto));
    }

    @ValidAspect
    @PostMapping("/auth/oauth2/signup")
    public ResponseEntity<?> oAuth2Signup(@Valid @RequestBody OAuth2SignupRequestDto OAuth2SignupRequestDto, BindingResult bindingResult) {
        adminAuthService.oAuth2Signup(OAuth2SignupRequestDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/auth/oauth2/merge")
    public ResponseEntity<?> oAuth2Merge(@RequestBody OAuth2MergeRequestDto oAuth2MergeRequestDto) {
        adminAuthService.oAuth2Merge(oAuth2MergeRequestDto);
        return ResponseEntity.ok(true);
    }
}
