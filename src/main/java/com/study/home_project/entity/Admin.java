package com.study.home_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private int adminId;
    private String adminName;
    private String adminPassword;
    private String tradeName;
    private String email;
    private int feedbackUse;
    private int playUse;
    private String imgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<AdminRoleRegister> adminRoleRegisters;
    private List<OAuth2> oAuth2s;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (AdminRoleRegister adminRoleRegister : adminRoleRegisters) {
            authorities.add(new SimpleGrantedAuthority(adminRoleRegister.getRole().getRoleName()));
        }
        return authorities;
    }

    public PrincipalUser toPrincipalUser() {
        return PrincipalUser.builder()
                .userId(adminId)
                .username(adminName)
                .tradename(tradeName)
                .email(email)
                .feedbackUse(feedbackUse)
                .playUse(playUse)
                .imgUrl(imgUrl)
                .authorities(getAuthorities())
                .build();
    }
}
