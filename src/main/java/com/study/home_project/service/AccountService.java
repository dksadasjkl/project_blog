package com.study.home_project.service;

import com.study.home_project.dto.AccountEditNicknameRequestDto;
import com.study.home_project.dto.AccountEditPasswordRequestDto;
import com.study.home_project.entity.User;
import com.study.home_project.repository.UserMapper;
import com.study.home_project.security.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userMapper.findUserByUsername(authentication.getName());
    }

    public void editPassword(AccountEditPasswordRequestDto accountEditPasswordRequestDto) {
        User user = getCurrentUser();

        if(!passwordEncoder.matches(accountEditPasswordRequestDto.getOldPassword(), user.getPassword())) {
            throw new ValidException(Map.of("oldPassword", "비밀번호 인증에 실패하였습니다. 다시 입력하세요"));
        }
        if(!accountEditPasswordRequestDto.getNewPassword().equals(accountEditPasswordRequestDto.getNewPasswordCheck())) {
            throw new ValidException(Map.of("newPasswordCheck", "새로운 비밀번호가 서로 일치하지 않습니다. 다시 입력하세요"));
        }
        if(passwordEncoder.matches(accountEditPasswordRequestDto.getNewPassword(), user.getPassword())) {
            throw new ValidException(Map.of("newPasswordCheck", "이전 비밀번호와 동일한 비밀번호는 사용하실 수 없습니다. 다시 입력하세요"));
        }
        user.setPassword(passwordEncoder.encode(accountEditPasswordRequestDto.getNewPassword()));
        userMapper.editPassword(user);
    }

//    @Transactional(rollbackFor = Exception.class)
//    public void editNicknameAndProfileImageUrl(AccountEditNicknameRequestDto accountEditNicknameRequestDto) {
//        User user = getCurrentUser();
//
//        if(userMapper.findUserByNickname(accountEditNicknameRequestDto.getNewNickname()) != null) {
//            throw new ValidException(Map.of("newNickname", "입력한 닉네임은 이미 다른 사용자에 의해 사용 중입니다. \n 다른 닉네임을 입력해주세요."));
//        }
//
//        user.setNickname(accountEditNicknameRequestDto.getNewNickname());
//        user.setProfileImageUrl(accountEditNicknameRequestDto.getNewProfileImageUrl());
//        userMapper.editNickname(user);
//        userMapper.updateProfileImageUrl(user);
//    }
}
