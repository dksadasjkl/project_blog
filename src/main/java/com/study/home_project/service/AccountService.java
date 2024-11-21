package com.study.home_project.service;

import com.study.home_project.dto.*;
import com.study.home_project.entity.Admin;
import com.study.home_project.entity.User;
import com.study.home_project.repository.AdminMapper;
import com.study.home_project.repository.UserMapper;
import com.study.home_project.security.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public void editPassword(EditPasswordRequestDto editPasswordReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminMapper.findAdminByUsername(authentication.getName());
        if(!passwordEncoder.matches(editPasswordReqDto.getOldPassword(),admin.getAdminPassword())) {
            throw new ValidException(Map.of("oldPassword", "비밀번호 인증에 실패하였습니다. \n다시입력하세요."));
        }
        if(!editPasswordReqDto.getNewPassword().equals(editPasswordReqDto.getNewPasswordCheck())) {
            throw new ValidException(Map.of("newPasswordCheck", "새로운 비밀번호가 서로 일치하지 않습니다.\n다시입력하세요."));
        }
        if(passwordEncoder.matches(editPasswordReqDto.getNewPassword(),admin.getAdminPassword())) {
            throw new ValidException(Map.of("newPassword","이전 비밀번호와 동일한 비밀번호는 사용하실 수 없습니다.\n다시입력하세요"));
        }

        admin.setAdminPassword(passwordEncoder.encode(editPasswordReqDto.getNewPassword()));
        adminMapper.modifyPassword(admin);
    }

    public void updateLogo(AdminLogoRequestDto adminLogoReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminMapper.findAdminByUsername(authentication.getName());
        System.out.println(admin);
        admin.setImgUrl(adminLogoReqDto.getImgUrl());
        adminMapper.updateLogo(admin);
    }

    public void checkPassword(CheckPasswordRequestDto checkPasswordReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminMapper.findAdminByUsername(authentication.getName());
        if (!passwordEncoder.matches(checkPasswordReqDto.getPassword(), admin.getAdminPassword())) {
            throw new ValidException(Map.of("oldPassword", "비밀번호 인증에 실패하였습니다. \n다시입력하세요."));
        }
        admin.setAdminPassword(passwordEncoder.encode(checkPasswordReqDto.getPassword()));
        adminMapper.modifyPassword(admin);
    }

    public int storeSettingChange(AdminStoreSettingRequestDto adminStoreSettingReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminMapper.findAdminByUsername(authentication.getName());
        admin.setFeedbackUse(adminStoreSettingReqDto.getFeedbackUse());
        admin.setPlayUse(adminStoreSettingReqDto.getPlayUse());
        return adminMapper.storeSettingChange(admin);
    }

    public void editTradeName(EditTradeNameRequestDto editTradeNameReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminMapper.findAdminByUsername(authentication.getName());
        admin.setTradeName(editTradeNameReqDto.getTradeName());
        adminMapper.modifyTradeName(admin);
    }

    public List<User> getAllUser() {
        return adminMapper.getUserAuth();
    }
}
