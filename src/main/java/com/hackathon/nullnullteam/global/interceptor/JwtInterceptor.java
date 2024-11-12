package com.hackathon.nullnullteam.global.interceptor;

import com.hackathon.nullnullteam.global.auth.JwtProvider;
import com.hackathon.nullnullteam.global.exception.AuthenticationException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final JwtProvider jwtProvider;

    public JwtInterceptor(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (authHeader == null) {
            return true;
        }
        if (!authHeader.startsWith(TOKEN_PREFIX)) {
            throw new AuthenticationException("Invalid Token");
        }

        Claims claim = jwtProvider.getClaim(authHeader.substring(7));
        request.setAttribute("userId", claim.getSubject());
        request.setAttribute("email", claim.get("email"));

        System.out.println("userId: " + claim.getSubject());
        return true;
    }
}
