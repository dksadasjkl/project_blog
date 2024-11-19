package com.study.home_project.service;

import com.study.home_project.dto.AdminSigninRequestDto;
import com.study.home_project.dto.AdminSignupRequestDto;
import com.study.home_project.entity.Admin;
import com.study.home_project.jwt.JwtProvider;
import com.study.home_project.repository.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminAuthService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;


    @Transactional(rollbackFor = Exception.class)
    public void adminSignup(AdminSignupRequestDto adminSignupReqDto) {
        int successCount = 0;
        Admin admin = adminSignupReqDto.toEntity(passwordEncoder);

        successCount += adminMapper.saveAdmin(admin);
        successCount += adminMapper.saveRole(admin.getAdminId(),1);

        if(successCount < 2) {
            throw new RuntimeException("데이터 저장 오류");
        }
    }

    public String adminSignin(AdminSigninRequestDto adminSigninRequestDto) {
        Admin admin = adminMapper.findAdminByUsername(adminSigninRequestDto.getUsername());
        if(admin == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요");
        }
        if(!passwordEncoder.matches(adminSigninRequestDto.getPassword(), admin.getAdminPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요");
        }
        return jwtProvider.generateToken(admin);
    }

}
