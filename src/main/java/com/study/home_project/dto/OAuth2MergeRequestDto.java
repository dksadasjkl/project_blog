package com.study.home_project.dto;

import lombok.Data;

@Data
public class OAuth2MergeRequestDto {
    private String username;
    private String password;
    private String oauth2Name;
    private String providerName;
}
