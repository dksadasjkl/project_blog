package com.study.home_project.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AdminFeedbackResponseDto {
    private int feedbackId;
    private int answer1;
    private int answer2;
    private int answer3;
    private LocalDateTime createDate;
}
