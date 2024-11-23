package com.study.home_project.controller;


import com.study.home_project.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMenuController {

    @Autowired
    private UserMenuService userMenuService;

    @GetMapping("/menus")
    public ResponseEntity<?> getAllMenu(@RequestParam int categoryId) {
        return ResponseEntity.ok().body(userMenuService.getAllMenu(categoryId));
    }

    @GetMapping("/menus/category")
    public ResponseEntity<?> getCategory() {
        return ResponseEntity.ok().body(userMenuService.getCategory());
    }

}
