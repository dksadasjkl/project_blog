package com.study.home_project.controller;


import com.study.home_project.annotation.ValidAspect;
import com.study.home_project.dto.request.*;
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
@RequestMapping("/admin/account")
public class AdminAccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
    }
    @ValidAspect
    @PutMapping("/password")
    public ResponseEntity<?> editPassword(@Valid @RequestBody EditPasswordRequestDto editPasswordReqDto, BindingResult bindingResult) {
        accountService.editPassword(editPasswordReqDto);
        return ResponseEntity.ok(true);
    }
    @ValidAspect
    @PutMapping("/password/check")
    public ResponseEntity<?> checkPassword(@Valid @RequestBody CheckPasswordRequestDto checkPasswordReqDto, BindingResult bindingResult) {
        accountService.checkPassword(checkPasswordReqDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/logo")
    public ResponseEntity<?> updateImg(@RequestBody AdminLogoRequestDto adminLogoReqDto) {
        accountService.updateLogo(adminLogoReqDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/storesetting")
    public ResponseEntity<?> storeSettingChange(@RequestBody AdminStoreSettingRequestDto adminStoreSettingReqDto){

        return ResponseEntity.ok(accountService.storeSettingChange(adminStoreSettingReqDto));
    }
    @PutMapping("tradename")
    public ResponseEntity<?> editTradeName(@Valid @RequestBody EditTradeNameRequestDto editTradeNameReqDto, BindingResult bindingResult) {
        accountService.editTradeName(editTradeNameReqDto);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getUserAuth() {
        return ResponseEntity.ok().body(accountService.getAllUser());
    }
}
