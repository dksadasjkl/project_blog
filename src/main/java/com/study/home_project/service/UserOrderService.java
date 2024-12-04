package com.study.home_project.service;

import com.study.home_project.dto.request.UserOrderMenusRequestDto;
import com.study.home_project.entity.OrderList;
import com.study.home_project.repository.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public void orderMenus(List<UserOrderMenusRequestDto> userOrderMenusReqDtos) {
        OrderList orderList = new OrderList();

        orderMapper.addOrderList(orderList);
        orderMapper.addOrders(userOrderMenusReqDtos.stream().map(userOrderMenusReqDto -> userOrderMenusReqDto.toEntity(orderList.getOrderListId())).collect(Collectors.toList()));
    }
}
