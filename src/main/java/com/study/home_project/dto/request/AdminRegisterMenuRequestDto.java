package com.study.home_project.dto.request;


import com.study.home_project.entity.Menu;
import lombok.Data;

@Data
public class AdminRegisterMenuRequestDto {
    private String menuName;
    private int categoryId;
    private String categoryName;
    private int menuPrice;
    private int menuCal;
    private int menuId;
    private String menuImgUrl;

    public Menu toEntity() {
        return Menu.builder()
                .menuName(menuName)
                .categoryId(categoryId)
                .menuId(menuId)
                .menuName(menuName)
                .categoryName(categoryName)
                .menuPrice(menuPrice)
                .menuCal(menuCal)
                .menuImgUrl(menuImgUrl)
                .build();
    }
}
