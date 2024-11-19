package com.study.home_project.jwt;

import com.study.home_project.entity.Admin;
import com.study.home_project.entity.PrincipalUser;
import com.study.home_project.entity.User;
import com.study.home_project.repository.AdminMapper;
import com.study.home_project.repository.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Collection;
import java.util.Date;


@Component
public class JwtProvider {

    private final Key key;
    private AdminMapper adminMapper;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Autowired AdminMapper adminMapper) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.adminMapper = adminMapper;
    }

    public String generateToken(Admin admin) {
        Collection<? extends GrantedAuthority> authorities = admin.getAuthorities();
        Date expireDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 24));

        String accessToken = Jwts.builder()
                .claim("userId", admin.getAdminId())
                .claim("username", admin.getAdminName())
                .claim("authorities", authorities)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return accessToken;
    }

    public String removeBearer(String token) {
        if(!StringUtils.hasText(token)) {
            return null;
        }
        return token.substring("Bearer ".length());
    }

    public Claims getClaims(String token) {
        Claims claims = null;
        claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token) // 토큰을 클래임으로 변환하는 작업
                .getBody();
        return claims;
    }

    public Authentication getAuthentication(Claims claims) {
        String username = claims.get("username").toString();
        Admin admin = adminMapper.findAdminByUsername(username);
        if(admin == null) {
            return null;
        }
        PrincipalUser principalUser = admin.toPrincipalUser();
        return new UsernamePasswordAuthenticationToken(principalUser, principalUser.getPassword(), principalUser.getAuthorities());
    }

//    public String generateAuthMailToken(String toMailAddress) {
//        Date exprireDate = new Date(new Date().getTime() + (1000 * 60 * 5));
//        return Jwts.builder()
//                .claim("toMailAddress",toMailAddress)
//                .setExpiration(exprireDate)
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
}
