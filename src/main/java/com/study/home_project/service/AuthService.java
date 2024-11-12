package com.study.home_project.service;

import com.study.home_project.dto.AuthSignupRequestDto;
import com.study.home_project.entity.User;
import com.study.home_project.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void signup(AuthSignupRequestDto authSignupRequestDto) {
        int successCount = 0;
        User user = authSignupRequestDto.toEntity(passwordEncoder);
        successCount += userMapper.saveUser(user);
        successCount += userMapper.saveRole(user.getUserId(), 1);

        if(successCount < 2) {
            throw new RuntimeException("데이터 저장 오류");
        }
    }

    public void signin(AuthSignupRequestDto authSignupRequestDto) {
        System.out.println(authSignupRequestDto);
    }
}
