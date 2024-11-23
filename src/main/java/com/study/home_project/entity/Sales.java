package com.study.home_project.entity;


import com.study.home_project.dto.response.AdminSalesResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    private int year;
    private int month;
    private int orderCount;
    private int totalSales;

    public AdminSalesResponseDto toAdminSalesRespDto() {
        return AdminSalesResponseDto.builder()
                .year(year)
                .month(month)
                .orderCount(orderCount)
                .totalSales(totalSales)
                .build();
    }
}
