package com.study.home_project.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int userId;
    private String username;
    private String password;
    private String name;
    private String telNumber;
    private String nickname;
    private String profileImageUrl;
}
