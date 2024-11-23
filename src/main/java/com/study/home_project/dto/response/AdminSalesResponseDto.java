package com.study.home_project.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminSalesResponseDto {
    private int year;
    private int month;
    private int orderCount;
    private int totalSales;
}
