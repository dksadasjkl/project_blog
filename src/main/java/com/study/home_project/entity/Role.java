package com.study.home_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
// 권한
public class Role {
    private int roleId;
    private String roleName;
    private String roleNameKor;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
