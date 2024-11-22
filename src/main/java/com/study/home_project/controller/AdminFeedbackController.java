package com.study.home_project.controller;


import com.study.home_project.service.AdminFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminFeedbackController {

    @Autowired
    private AdminFeedbackService adminFeedbackService;

    @GetMapping("/feedback/answer")
    public ResponseEntity<?> adminOrderFeedback() {
        return ResponseEntity.ok().body(adminFeedbackService.findFeedbacks());
    }

    @GetMapping("/feedback/answer-count")
    public ResponseEntity<?> adminFeedbackCount() {
        return ResponseEntity.ok().body(adminFeedbackService.feedbackCount());
    }
}
