package com.study.home_project.dto.request;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class AccountEditNicknameRequestDto {
    @Pattern(regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$", message = "2자 이상 16자 이하, 영어 또는 숫자 또는 한글 형식이어야 합니다.")
    private String newNickname;
    private String newProfileImageUrl;
}
