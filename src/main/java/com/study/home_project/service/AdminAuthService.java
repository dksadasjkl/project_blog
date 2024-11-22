package com.study.home_project.service;

import com.study.home_project.dto.request.AdminSigninRequestDto;
import com.study.home_project.dto.request.AdminSignupRequestDto;
import com.study.home_project.dto.request.OAuth2MergeRequestDto;
import com.study.home_project.dto.request.OAuth2SignupRequestDto;
import com.study.home_project.entity.Admin;
import com.study.home_project.entity.OAuth2;
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

    @Transactional(rollbackFor = Exception.class)
    public void oAuth2Signup(OAuth2SignupRequestDto oAuth2SignupRequestDto) {
        int successCount = 0;
        Admin admin = oAuth2SignupRequestDto.toEntity(passwordEncoder);
        successCount += adminMapper.saveAdmin(admin);
        successCount += adminMapper.saveRole(admin.getAdminId(),3);
        successCount += adminMapper.saveOAuth2(oAuth2SignupRequestDto.toOAuth2(admin.getAdminId()));

        if(successCount < 3) {
            throw new RuntimeException("데이터 저장 오류");
        }
    }

    public void oAuth2Merge(OAuth2MergeRequestDto oAuth2MergeRequestDto) {
        Admin admin = adminMapper.findAdminByUsername(oAuth2MergeRequestDto.getUsername());

        if(admin == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요");
        }
        if(!passwordEncoder.matches(oAuth2MergeRequestDto.getPassword(),admin.getAdminPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요");
        }
        OAuth2 oAuth2 = OAuth2.builder()
                .oAuth2Name(oAuth2MergeRequestDto.getOauth2Name())
                .adminId(admin.getAdminId())
                .providerName(oAuth2MergeRequestDto.getProviderName())
                .build();
        adminMapper.saveOAuth2(oAuth2);
    }

}
