package com.study.home_project.service;

import com.study.home_project.entity.Category;
import com.study.home_project.entity.Order;
import com.study.home_project.repository.MenuMapper;
import com.study.home_project.repository.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private OrderMapper orderMapper;


    // categoryId == 1일 때는 메뉴, categoryId == 2일 때는 주문 랭킹, categoryId == 3일 때는 버거 메뉴를 반환하고, 그 외에는 음료 메뉴를 반환합니다.
    public List<?> getAllMenu(int categoryId) {
        if (categoryId == 1) {
            return menuMapper.getMenus();
        } else if (categoryId == 2) {
            List<Order> orders = orderMapper.getRanking();

            return orders;
        } else if (categoryId == 3) {
            return menuMapper.getHotCoffee();
        } else if (categoryId == 4) {
            return menuMapper.getIceCoffee();
        } else if (categoryId == 5) {
            return menuMapper.getSmoothie();
        } else if (categoryId == 6) {
            return menuMapper.getAde();
        }
        return menuMapper.getTea();
    }

    public List<Category> getCategory() {
        return menuMapper.getCategory();
    }
}