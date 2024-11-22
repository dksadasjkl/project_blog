package com.study.home_project.service;

import com.study.home_project.dto.response.AdminFeedbackCountResponseDto;
import com.study.home_project.dto.response.AdminFeedbackResponseDto;
import com.study.home_project.entity.Feedback;
import com.study.home_project.repository.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminFeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    public List<AdminFeedbackResponseDto> findFeedbacks() {
        List<Feedback> feedbacks = feedbackMapper.findFeedback();
        return feedbacks.stream().map(Feedback::toFeedbackRespDto).collect(Collectors.toList());

    }

    public List<AdminFeedbackCountResponseDto> feedbackCount() {
        List<Feedback> feedbackCount = feedbackMapper.feedbackCount();
        return feedbackCount.stream().map(Feedback::toFeedbackCountRespDto).collect(Collectors.toList());
    }
}
