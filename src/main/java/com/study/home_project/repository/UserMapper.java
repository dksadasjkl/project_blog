package com.study.home_project.repository;

import com.study.home_project.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    public int saveUser(User user);
    public int saveRole(@Param("userId") int userId, @Param("roleId") int roleId);
    public User findUserByUsername(String username);
}
