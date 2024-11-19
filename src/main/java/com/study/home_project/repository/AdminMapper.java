package com.study.home_project.repository;


import com.study.home_project.entity.Admin;
import com.study.home_project.entity.OAuth2;
import com.study.home_project.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    public int saveAdmin(Admin admin);
    public Admin findAdminByUsername(String username);
    public int saveRole(@Param("adminId") int adminId, @Param("roleId") int roleId);
    public int modifyPassword(Admin admin);
    public int saveOAuth2(OAuth2 oAuth2);
    public Admin findAdminByOAuth2name(String oAuth2name);
    public List<User> getUserAuth();
    public int updateLogo(Admin admin);
    public int storeSettingChange(Admin admin);
    public int modifyTradeName(Admin admin);
}