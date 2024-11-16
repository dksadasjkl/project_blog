package com.study.home_project.service;

import com.study.home_project.dto.AuthSigninRequestDto;
import com.study.home_project.dto.AuthSignupRequestDto;
import com.study.home_project.dto.OAuth2SignupRequestDto;
import com.study.home_project.entity.User;
import com.study.home_project.jwt.JwtProvider;
import com.study.home_project.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    
    // 회원가입 기능
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
    
    
    // 로그인 기능
    public String signin(AuthSigninRequestDto authSigninRequestDto) {
        User user = userMapper.findUserByUsername(authSigninRequestDto.getUsername());
        if(user == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요");
        }
        if(!passwordEncoder.matches(authSigninRequestDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요");
        }

        return jwtProvider.generateToken(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void oAuth2Signup(OAuth2SignupRequestDto oAuth2SignupRequestDto) {
        int successCount = 0;
        User user = oAuth2SignupRequestDto.toEntity(passwordEncoder);
        successCount += userMapper.saveUser(user);
        successCount += userMapper.saveRole(user.getUserId(), 1);
        successCount += userMapper.saveOAuth2(oAuth2SignupRequestDto.toOAuth2(user.getUserId()));

        if(successCount < 3) {
            throw new RuntimeException("데이터 저장 오류");
        }

    }

}
