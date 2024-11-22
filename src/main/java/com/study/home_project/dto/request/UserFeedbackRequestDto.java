package com.study.home_project.dto.request;


import com.study.home_project.entity.Feedback;
import lombok.Data;

@Data
public class UserFeedbackRequestDto {
    private int answer1;
    private int answer2;
    private int answer3;

    public Feedback toEntity() {
        return Feedback.builder()
                .answer1(answer1)
                .answer2(answer2)
                .answer3(answer3)
                .build();
    }
}
