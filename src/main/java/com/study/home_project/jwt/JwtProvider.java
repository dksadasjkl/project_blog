package com.study.home_project.jwt;

import com.study.home_project.entity.PrincipalUser;
import com.study.home_project.entity.User;
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
    public UserMapper userMapper;

    public JwtProvider(@Value("${jwt.secret}") String secret, @Autowired UserMapper userMapper)  {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper;
    }

    public String generateToken(User user) {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        Date expireDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 24));

        String accessToken = Jwts.builder()
                .claim("userId", user.getUserId())
                .claim("username", user.getUsername())
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
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public Authentication getAuthentication(Claims claims) {
        String username = claims.get("username").toString();
        User user = userMapper.findUserByUsername(username);
        if(user == null) {
            return null;
        }
        PrincipalUser principalUser = user.toPrincipalUser();
        return new UsernamePasswordAuthenticationToken(principalUser, principalUser.getPassword(), principalUser.getAuthorities());
    }
}
