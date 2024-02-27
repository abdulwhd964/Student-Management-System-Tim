
package com.studentmanagement.util.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.studentmanagement.constant.JWTConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class JwtTokenUtil {

    static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437JAGS123";

    @Value("${token.expire.time:1}")
    int expireTime;

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        String token = createToken(claims, userName);
        log.info("Token : " + token);
        return token;
    }

    private String createToken(Map<String, Object> claims, String userName) {

        Date issDate = new Date(System.currentTimeMillis());
        Date expDate = DateUtils.addDays(issDate, expireTime);

        return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(issDate).setExpiration(expDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public String validateToken(final String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
            if (ObjectUtils.isEmpty(claims))
                return JWTConstant.TOKEN_INVALID.getMessage();
        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException Occurred : ", e.getMessage());
            return JWTConstant.TOKEN_EXPIRED.getMessage();
        } catch (Exception e) {
            log.error("Exception Occurred : ", e.getMessage());
            return JWTConstant.TOKEN_EXPIRED.getMessage();
        }
        return JWTConstant.TOKEN_VALID.getMessage();
    }

}
