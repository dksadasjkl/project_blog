package com.study.home_project.dto.request;


import com.study.home_project.entity.Menu;
import lombok.Data;

@Data
public class AdminUpdateMenuRequestDto {
    private int menuId;
    private String menuName;
    private int categoryId;
    private int menuPrice;
    private int menuCal;
    private String menuImgUrl;

    public Menu toEntity() {
        return Menu.builder()
                .menuId(menuId)
                .menuName(menuName)
                .categoryId(categoryId)
                .menuPrice(menuPrice)
                .menuCal(menuCal)
                .menuImgUrl(menuImgUrl)
                .build();
    }
}
