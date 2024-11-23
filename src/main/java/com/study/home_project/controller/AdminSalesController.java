package com.study.home_project.controller;


import com.study.home_project.service.AdminSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminSalesController {

    @Autowired
    private AdminSalesService adminSalesService;

    @GetMapping("/sales")
    public ResponseEntity<?> getSales() {
        return ResponseEntity.ok().body(adminSalesService.getSales());
    }

    @GetMapping("/menusales")
    public ResponseEntity<?> getSalesByMenu() {
        return ResponseEntity.ok(adminSalesService.getSalesByMenu());
    }
}
