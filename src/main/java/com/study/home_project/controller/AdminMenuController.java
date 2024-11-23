package com.study.home_project.controller;

import com.study.home_project.dto.request.AdminRegisterMenuRequestDto;
import com.study.home_project.dto.request.AdminUpdateMenuRequestDto;
import com.study.home_project.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminMenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    @PostMapping("/menu")
    public ResponseEntity<?> registerMenu(@RequestBody AdminRegisterMenuRequestDto adminRegisterMenuReqDto) {
        adminMenuService.saveMenu(adminRegisterMenuReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @GetMapping("/menus")
    public ResponseEntity<?> getMenu() {
        return ResponseEntity.ok(adminMenuService.getMenus());
    }

    @PutMapping("/menu")
    public ResponseEntity<?> updateMenu(@RequestBody AdminUpdateMenuRequestDto adminUpdateMenuReqDto) {
        adminMenuService.updateMenu(adminUpdateMenuReqDto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/menu")
    public ResponseEntity<?> deleteMenu(@RequestParam int menuId) {
        adminMenuService.deleteMenu(menuId);
        return ResponseEntity.ok(true);
    }
}