package com.study.home_project.repository;

import com.study.home_project.entity.MenuSales;
import com.study.home_project.entity.Sales;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesMapper {
    public List<Sales> getSales();
    public List<MenuSales> findSales();
}
