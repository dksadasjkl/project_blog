package com.study.home_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSales {
    private int menuId;
    private String orderYear;
    private String orderMonth;
    private int totalCount;
    private int sales;
}
