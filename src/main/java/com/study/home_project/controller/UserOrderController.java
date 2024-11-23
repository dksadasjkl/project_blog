package com.study.home_project.controller;

import com.study.home_project.dto.request.UserOrderMenusRequestDto;
import com.study.home_project.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("/order")
    public ResponseEntity<?> orderMenus(@RequestBody List<UserOrderMenusRequestDto> userOrderMenusReqDto) {
        userOrderService.orderMenus(userOrderMenusReqDto);
        return ResponseEntity.created(null).body(true);
    }
}
