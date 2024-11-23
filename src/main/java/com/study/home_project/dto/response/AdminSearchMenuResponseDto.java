package com.study.home_project.dto.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminSearchMenuResponseDto {
    private int menuId;
    private String menuName;
    private int menuPrice;
    private int menuCal;
    private String menuImgUrl;
    private String categoryName;
    private String categoryNameKor;
}
