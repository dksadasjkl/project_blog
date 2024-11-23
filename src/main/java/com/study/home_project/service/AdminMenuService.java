package com.study.home_project.service;


import com.study.home_project.dto.request.AdminRegisterMenuRequestDto;
import com.study.home_project.dto.request.AdminUpdateMenuRequestDto;
import com.study.home_project.dto.response.AdminSearchMenuResponseDto;
import com.study.home_project.entity.Menu;
import com.study.home_project.repository.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Transactional(rollbackFor = Exception.class)
    public int saveMenu(AdminRegisterMenuRequestDto adminRegisterMenuReqDto) {
        return menuMapper.saveMenu(adminRegisterMenuReqDto.toEntity());
    }

    public List<AdminSearchMenuResponseDto> getMenus() {
        List<Menu> menus = menuMapper.getMenus();

        return menus.stream().map(Menu::toSearchMenuRespDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateMenu(AdminUpdateMenuRequestDto adminUpdateMenuReqDto) {
        return menuMapper.updateMenuByMenuId(adminUpdateMenuReqDto.toEntity());
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteMenu(int menuId) {
        return menuMapper.deleteMenuByMenuId(menuId);
    }
}
