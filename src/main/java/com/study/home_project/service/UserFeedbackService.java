package com.study.home_project.service;

import com.study.home_project.dto.request.UserFeedbackRequestDto;
import com.study.home_project.repository.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveFeedback(UserFeedbackRequestDto userFeedbackRequestDto) {
        feedbackMapper.saveFeedback(userFeedbackRequestDto.toEntity());
    }

}