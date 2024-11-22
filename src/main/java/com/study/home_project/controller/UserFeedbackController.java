package com.study.home_project.controller;


import com.study.home_project.dto.request.UserFeedbackRequestDto;
import com.study.home_project.service.UserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFeedbackController {

    @Autowired
    private UserFeedbackService userFeedbackService;

    @PostMapping("/feedback/add")
    public ResponseEntity<?> saveFeedback(@RequestBody UserFeedbackRequestDto userFeedbackRequestDto) {
        userFeedbackService.saveFeedback(userFeedbackRequestDto);
        return ResponseEntity.ok().body(true);
    }
}
