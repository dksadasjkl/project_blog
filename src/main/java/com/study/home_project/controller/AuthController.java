package com.study.home_project.controller;

import com.study.home_project.annotation.ValidAspect;
import com.study.home_project.dto.*;
import com.study.home_project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    
    // 회원 가입 기능(전화번호 가입)
//    @ValidAspect
//    @PostMapping("/sign-up")
//    public ResponseEntity<?> signup(@Valid @RequestBody AuthSignupRequestDto authSignupRequestDto, BindingResult bindingResult) {
//        authService.signup(authSignupRequestDto);
//        return ResponseEntity.created(null).body(true);
//    }


    // 로그인 기능
//    @PostMapping("/sign-in")
//    public ResponseEntity<?> signin(@RequestBody AuthSigninRequestDto authSigninRequestDto) {
//        return ResponseEntity.created(null).body(authService.signin(authSigninRequestDto));
//    }

//    // 소설 회원가입 기능
//    @ValidAspect
//    @PostMapping("/oauth2/sign-up")
//    public ResponseEntity<?> oAuth2Signup(@Valid @RequestBody OAuth2SignupRequestDto oAuth2SignupRequestDto, BindingResult bindingResult) {
//        authService.oAuth2Signup(oAuth2SignupRequestDto);
//        return ResponseEntity.created(null).body(true);
//    }

    @ValidAspect
    @PostMapping("/username-check")
    public ResponseEntity<?> usernameCheck(@Valid @RequestBody AuthUsernameCheckRequestDto authUsernameCheckRequestDto, BindingResult bindingResult) {
        return ResponseEntity.created(null).body(Map.of("username","사용할 수 있는 유저네임입니다."));
    }

    @ValidAspect
    @PostMapping("/nickname-check")
    public ResponseEntity<?> nicknameCheck(@Valid @RequestBody AuthNicknameCheckRequestDto authNicknameCheckRequestDto, BindingResult bindingResult) {
        return ResponseEntity.created(null).body(Map.of("newNickname","사용할 수 있는 닉네임입니다."));
    }



}
