package com.study.home_project.security.filter;

import com.study.home_project.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Component
public class JwtAuthenticationFilter extends GenericFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        List<String> antMatchers = List.of(
                "/server",
                "/admin/**",
                "/error",
                "/oauth2/**",
                "/auth/**",
                "/auth/signup",
                "/admin/auth/signup",
                "/admin/auth/signin",
                "/admin/auth/oauth2/signup",
                "/admin/auth/oauth2/signin",
                "/admin/auth/oauth2/merge",
                "/menus"
        );

        String uri = request.getRequestURI();
        request.setAttribute("isPermitAll", false);
        for(String antMatcher : antMatchers) {
            if(uri.startsWith(antMatcher)) {
                request.setAttribute("isPermitAll", true);
            }
        }

        Boolean isPermitAll = (Boolean) request.getAttribute("isPermitAll");

        if(!isPermitAll) {
            String accessToken = request.getHeader("Authorization");
            String removeBearerToken = jwtProvider.removeBearer(accessToken);
            Claims claims = null;
            try {
                claims = jwtProvider.getClaims(removeBearerToken);
            } catch (Exception e) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            }
            Authentication authentication = jwtProvider.getAuthentication(claims);

            if(authentication == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
