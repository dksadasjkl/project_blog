package com.study.home_project.dto;

import com.study.home_project.entity.Admin;
import lombok.Data;


@Data
public class AdminStoreSettingRequestDto {
    private int feedbackUse;
    private int playUse;

    public Admin toEntity() {
        return Admin.builder()
                .feedbackUse(feedbackUse)
                .playUse(playUse)
                .build();
    }
}
