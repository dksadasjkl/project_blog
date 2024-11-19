package com.study.home_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRegister {
    private int roleRegisterId;
    private int userId;
    private int roleId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Role role;
}
