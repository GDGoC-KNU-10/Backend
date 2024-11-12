package com.hackathon.nullnullteam.global.auth;

import com.hackathon.nullnullteam.global.exception.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private long validityInMilliseconds = 60 * 60 * 1000;
    private String secretKey = "aefewgthrtbffsfsdafwfqqqdqefewfsvawdqdwdqwfqeggdshshafda";

    public String createToken(Long id, String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims getClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (SecurityException | MalformedJwtException e) {
            throw new JwtException("유효하지 않은 토큰입니다.");
        } catch (ExpiredJwtException e) {
            throw new JwtException("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("지원되지 않는 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new JwtException("토큰이 존재하지 않습니다.");
        } catch (Exception e) {
            throw new AuthenticationException("토큰 에러");
        }
    }
}
