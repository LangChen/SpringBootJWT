package com.chlang.common.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtHelper {

//    @Value("${jwt.key}")
//    private String key;

    /**
     * 创建Token
     * @param userAccount
     * @return
     */
    public String createToken(String userAccount){
        //设置过期时间，如果使用redis，可以去掉，
        Map<String,Object> claims = new HashMap<>();
        claims.put("userAccount","test");  //setSubject
        Date now = new Date();
        Date exp = new Date(now.getTime()+10000);

        String token = Jwts.builder()
                .setClaims(claims)
                .setId("123")
                .setExpiration(exp)
                .signWith(getKey())
                .compact();
        return token;
    }

    /**
     * 验证token的有效性
     * @param token
     */
    public Claims verifyToken(String token)throws Exception{
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
            //OK, we can trust this JWT
        } catch (JwtException e) {
            //don't trust the JWT!
            throw e;
        }
        return claims;
    }

    private SecretKey getKey(){
        //32个字节的密钥
        String secretString = "#key_74852_?_1231#12134567654312";
        return Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

}
