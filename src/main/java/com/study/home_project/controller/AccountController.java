package com.study.home_project.controller;

import com.study.home_project.annotation.ValidAspect;
import com.study.home_project.dto.AccountEditNicknameRequestDto;
import com.study.home_project.dto.AccountEditPasswordRequestDto;
import com.study.home_project.entity.PrincipalUser;
import com.study.home_project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    // 권한 유저 요청
    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
    }
    
//    @ValidAspect
//    @PutMapping("/edit/password")
//    public ResponseEntity<?> editPassword (@Valid @RequestBody AccountEditPasswordRequestDto accountEditPasswordRequestDto, BindingResult bindingResult) {
//        accountService.editPassword(accountEditPasswordRequestDto);
//        return ResponseEntity.ok(true);
//    }


//    @ValidAspect
//    @PutMapping("/edit/nickname/profileImg")
//    public ResponseEntity<?> editNicknameAndProfileImageUrl (@Valid @RequestBody AccountEditNicknameRequestDto accountEditNicknameRequestDto, BindingResult bindingResult) {
//        accountService.editNicknameAndProfileImageUrl(accountEditNicknameRequestDto);
//        return ResponseEntity.ok(true);
//    }
}
