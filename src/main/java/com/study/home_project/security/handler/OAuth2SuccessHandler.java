package com.study.home_project.security.handler;

import com.study.home_project.entity.User;
import com.study.home_project.jwt.JwtProvider;
import com.study.home_project.repository.UserMapper;
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
    private UserMapper userMapper;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String oAuth2name = authentication.getName();
        User user = userMapper.findUserByOAuth2name(oAuth2name);

        if (user == null) {
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            String providerName = oAuth2User.getAttribute("provider").toString();
            response.sendRedirect("http://" + clientAddress + "/auth/oauth2/sign-up?name=" + oAuth2name + "&provider=" + providerName);
            return;
        }

        String accessToken = jwtProvider.generateToken(user);
        response.sendRedirect("http://" + clientAddress + "/auth/oauth2/sign-in?accessToken=" + accessToken);
    }
}
