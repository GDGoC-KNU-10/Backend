package com.hackathon.nullnullteam.global.resolver;

import com.hackathon.nullnullteam.global.annotation.Authenticate;
import com.hackathon.nullnullteam.global.exception.AuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authenticate.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String userId = (String) request.getAttribute("userId");

        if (userId == null) {
            throw new AuthenticationException("로그인 후 이용해 주세요.");
        }
        return Long.parseLong(userId);
    }
}
