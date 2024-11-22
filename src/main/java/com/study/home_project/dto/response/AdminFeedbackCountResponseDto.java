package com.study.home_project.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminFeedbackCountResponseDto {
    private int q1a1;
    private int q1a2;
    private int q1a3;
    private int q2a1;
    private int q2a2;
    private int q2a3;
    private int q3a1;
    private int q3a2;
    private int q3a3;
}
