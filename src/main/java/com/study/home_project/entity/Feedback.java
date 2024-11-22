package com.study.home_project.entity;


import com.study.home_project.dto.response.AdminFeedbackCountResponseDto;
import com.study.home_project.dto.response.AdminFeedbackResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private int feedbackId;
    private int orderListId;
    private int answer1;
    private int answer2;
    private int answer3;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int q1a1;
    private int q1a2;
    private int q1a3;
    private int q2a1;
    private int q2a2;
    private int q2a3;
    private int q3a1;
    private int q3a2;
    private int q3a3;

    public AdminFeedbackResponseDto toFeedbackRespDto() {
        return AdminFeedbackResponseDto.builder()
                .feedbackId(feedbackId)
                .answer1(answer1)
                .answer2(answer2)
                .answer3(answer3)
                .createDate(createDate)
                .build();
    }

    public AdminFeedbackCountResponseDto toFeedbackCountRespDto() {
        return AdminFeedbackCountResponseDto.builder()
                .q1a1(q1a1)
                .q1a2(q1a2)
                .q1a3(q1a3)
                .q2a1(q2a1)
                .q2a2(q2a2)
                .q2a3(q2a3)
                .q3a1(q3a1)
                .q3a2(q3a2)
                .q3a3(q3a3)
                .build();
    }
}
