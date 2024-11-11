package com.study.home_project.service;

import com.study.home_project.dto.AuthSignupRequestDto;
import com.study.home_project.entity.User;
import com.study.home_project.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void signup(AuthSignupRequestDto authSignupRequestDto) {
        User user = authSignupRequestDto.toEntity(passwordEncoder);
        userMapper.saveUser(user);
    }

    public void signin(AuthSignupRequestDto authSignupRequestDto) {
        System.out.println(authSignupRequestDto);
    }
}
