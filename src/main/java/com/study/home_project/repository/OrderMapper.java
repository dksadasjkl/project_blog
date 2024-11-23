package com.study.home_project.repository;

import com.study.home_project.entity.Order;
import com.study.home_project.entity.OrderList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    public int addOrderList(OrderList orderList);
    public int addOrders(List<Order> orders);
    public List<Order> getRanking();

}
