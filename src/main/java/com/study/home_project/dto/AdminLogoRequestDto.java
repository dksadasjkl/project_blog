package com.study.home_project.dto;

import com.study.home_project.entity.Admin;
import lombok.Data;

@Data
public class AdminLogoRequestDto {
    private String imgUrl;

    public Admin toEntity(){
        return Admin.builder()
                .imgUrl(imgUrl)
                .build();
    }
}
