package com.study.home_project.controller;


import com.study.home_project.dto.request.UserUseOrSavePointRequestDto;
import com.study.home_project.service.UserPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserPointController {

    @Autowired
    private UserPointService userPointService;

    @PostMapping("/point")
    public ResponseEntity<?> useOrSavePoint(@RequestBody UserUseOrSavePointRequestDto userUseOrSavePointReqDto) {
        userPointService.useOrSavePointByNumber(userUseOrSavePointReqDto);
        return ResponseEntity.created(null).body(true);
    }
    @GetMapping("/point")
    public ResponseEntity<?> getPoint(@RequestParam String phoneNumber) {
        System.out.println(phoneNumber);
        return ResponseEntity.ok().body(userPointService.getPointByPhoneNumber(phoneNumber));
    }


}
