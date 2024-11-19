package com.study.home_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private int roleId;
    private String roleNameKor;
    private String phoneNumber;
    private int totalPoint;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<RoleRegister> roleRegisters;

//    public List<SimpleGrantedAuthority> getAuthorities() {
//        return roleRegisters.stream()
//                .map(roleRegister ->
//                        new SimpleGrantedAuthority(roleRegister.getRole().getRoleName()))
//                .collect(Collectors.toList());
//    }

//    public PrincipalUser toPrincipalUser() {
//        return PrincipalUser.builder()
//                .userId(userId)
//                .username(username)
//                .name(name)
//                .telNumber(telNumber)
//                .nickname(nickname)
//                .profileImageUrl(profileImageUrl)
//                .authorities(getAuthorities())
//                .build();
//    }
}
