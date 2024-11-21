package com.study.home_project.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CheckPasswordRequestDto {
    @NotBlank
    private String password;
}