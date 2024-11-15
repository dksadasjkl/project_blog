package com.study.home_project.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AccountEditPasswordRequestDto {
    @NotBlank(message = "현재 비밀번호를 입력해주세요")
    private String oldPassword;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "하나의 영문자, 숫자, 특수문자를 포함한 5 ~ 128자리 형식이어야 합니다")
    private String newPassword;
    @NotBlank(message = "새로운 비밀번호를 입력해주세요")
    private String newPasswordCheck;
}
