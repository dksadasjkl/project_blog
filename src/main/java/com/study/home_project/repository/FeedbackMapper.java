package com.study.home_project.repository;


import com.study.home_project.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedbackMapper {
    public int saveFeedback(Feedback feedback);
    public List<Feedback> findFeedback();
    public List<Feedback> feedbackCount();
}
