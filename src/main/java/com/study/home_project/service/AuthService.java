package com.study.home_project.service;

import com.study.home_project.dto.request.AuthSignupRequestDto;
import com.study.home_project.jwt.JwtProvider;
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
    @Autowired
    private JwtProvider jwtProvider;


    @Transactional(rollbackFor = Exception.class)
    public void signup(AuthSignupRequestDto authSignupRequestDto){
        userMapper.saveUser(authSignupRequestDto.toEntity());
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(int userId) {
        userMapper.deleteUser(userId);
    }


}
