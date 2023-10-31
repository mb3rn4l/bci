package com.bci.bci.layer.infrastructure.adapter.utils;

import com.bci.bci.layer.domain.utils.IJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.bci.bci.layer.domain.constants.UserConstants.EMAIL_KEY;

@Component
public class JwtUtil implements IJwt {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generateJWT(String email) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(EMAIL_KEY, email);

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    @Override
    public String getClaim(String token,String key) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return  (String) claims.get(key);
    }


}
