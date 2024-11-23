package com.study.home_project.service;

import com.study.home_project.dto.response.AdminSalesResponseDto;
import com.study.home_project.entity.MenuSales;
import com.study.home_project.entity.Sales;
import com.study.home_project.repository.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminSalesService {
    @Autowired
    private SalesMapper salesMapper;

    public List<AdminSalesResponseDto> getSales() {
        List<Sales> sales = salesMapper.getSales();
        return sales.stream().map(Sales::toAdminSalesRespDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public List<MenuSales> getSalesByMenu() {
        salesMapper.findSales();
        return salesMapper.findSales();
    }
}
