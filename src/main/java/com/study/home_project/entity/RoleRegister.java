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
public class RoleRegister {
    private int roleRegisterId;
    private int userId;
    private int roleId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Role role;
}
