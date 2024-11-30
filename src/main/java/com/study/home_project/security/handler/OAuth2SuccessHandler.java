package com.study.home_project.security.handler;

import com.study.home_project.entity.Admin;
import com.study.home_project.entity.User;
import com.study.home_project.jwt.JwtProvider;
import com.study.home_project.repository.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Value("${client.deploy-address}")
    private String clientAddress;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String oAuth2name = authentication.getName();
        Admin admin = adminMapper.findAdminByOAuth2name(oAuth2name);

        if (admin == null) {
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            String providerName = oAuth2User.getAttribute("provider").toString();
            response.sendRedirect("http://" + clientAddress + "/oauth2?name=" + oAuth2name + "&provider=" + providerName);
            return;
        }

        String accessToken = jwtProvider.generateToken(admin);
        response.sendRedirect("http://" + clientAddress + "/oauth2/signin?accessToken=" + accessToken);
    }
}
