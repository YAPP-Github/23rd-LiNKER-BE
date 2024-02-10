package com.imlinker.coreapi.core.auth.context;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthenticatedUserContextResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticatedUserContext.class)
                && parameter.getParameterType() == AuthenticatedUserContextHolder.class;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof AuthenticatedUserContextHolder)) {
            /**
             * oAuth 로그인 이후, SecurityContextHolder에 자동으로 CustomOAuth2User가 등록이 됨 별도의 JWT를 통한 인증/인가를 구현하고있기
             * 때문에, ArgumentResolver에서 TypeChecking
             */
            throw new ApplicationException(ErrorType.UNAUTHENTICATED);
        }

        return principal;
    }
}
