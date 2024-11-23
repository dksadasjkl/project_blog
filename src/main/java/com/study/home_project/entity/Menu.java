package com.study.home_project.entity;

import com.study.home_project.dto.response.AdminSearchMenuResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private int menuId;
    private String menuName;
    private int categoryId;
    private String categoryName;
    private String categoryNameKor;
    private int menuCount;
    private int menuPrice;
    private int menuCal;
    private String menuImgUrl;

    public AdminSearchMenuResponseDto toSearchMenuRespDto() {
        return AdminSearchMenuResponseDto.builder()
                .menuId(menuId)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuCal(menuCal)
                .menuImgUrl(menuImgUrl)
                .categoryName(categoryName)
                .categoryNameKor(categoryNameKor)
                .build();
    }
}

