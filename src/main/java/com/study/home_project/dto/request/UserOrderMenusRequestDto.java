package com.study.home_project.dto.request;

import com.study.home_project.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderMenusRequestDto {
    private int menuId;
    private int menuCount;

    public Order toEntity(int orderListId) {
        return Order.builder()
                .orderListId(orderListId)
                .menuId(menuId)
                .menuCount(menuCount)
                .build();
    }
}
