package com.study.home_project.controller;

import com.study.home_project.dto.request.AuthSignupRequestDto;
import com.study.home_project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody AuthSignupRequestDto authSignupRequestDto, BindingResult bindingResult) {
        authService.signup(authSignupRequestDto);
        return ResponseEntity.created(null).body(true);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> delete(@PathVariable int userId) {
        authService.delete(userId);
        return ResponseEntity.ok(true);
    }


}
