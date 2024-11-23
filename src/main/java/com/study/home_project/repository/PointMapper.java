package com.study.home_project.repository;

import com.study.home_project.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PointMapper {
    public User findUser(String phoneNumber);
    public Integer getPoint(String phoneNumber);
    public int useOrSavePoint(
            @Param("userId") int userId,
            @Param("point") int point);
}
