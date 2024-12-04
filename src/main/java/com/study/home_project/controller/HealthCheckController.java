package com.study.home_project.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//배포 서버 상태 체크
@RestController
public class HealthCheckController {
    @Value("${server.env}")
    private String env;

    @Value("${server.name}")
    private String serverName;

    @Value("${server.deploy-address}")
    private String deployAddress;

    @Value("${server.port}")
    private String port;

    @GetMapping("/server/hc")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "serverName",serverName,
                "deployAddress", deployAddress,
                "port", port
        ));
    }

    @GetMapping("/server/env")
    public ResponseEntity<?> getEnv() {
        return ResponseEntity.ok(env);
    }

}
