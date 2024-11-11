package com.study.home_project.dto;

import com.study.home_project.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AuthSignupRequestDto {
    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "영문자, 숫자 5 ~ 10자리 형식이어야 합니다")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "하나의 영문자, 숫자, 특수문자를 포함한 5 ~ 128자리 형식이어야 합니다")
    private String password;
    @Pattern(regexp = "^[가-힣]{1,}$", message = "한글문자 형식이어야 합니다")
    private String name;
    @NotBlank(message = "전화번호를 입력하세요")
    private String telNumber;
    @Pattern(regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$", message = "2자 이상 16자 이하, 영어 또는 숫자 또는 한글 형식이어야 합니다.")
    private String nickname;
    @NotBlank(message = "이미지를 선택해주세요.")
    private String profileImageUrl;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .telNumber(telNumber)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}
