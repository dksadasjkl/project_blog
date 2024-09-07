package com.study.home_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role {
    private int roleId;
    private String roleName;
    private String roleNameKor;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
