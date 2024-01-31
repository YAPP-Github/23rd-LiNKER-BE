package com.imlinker.coreapi.core.auth.security.oauth2;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final CustomClientOriginHostCache clientOriginHostCache;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        String originHost = getHostFromCache(request.getParameter("state"));
        getRedirectStrategy().sendRedirect(request, response, originHost + "/failure");
    }

    private String getHostFromCache(String state) {
        String host = clientOriginHostCache.getClientOriginHost(state);
        if (host == null) {
            throw new ApplicationException(
                    ErrorType.INTERNAL_PROCESSING_ERROR, "ClientHost를 찾을 수 없습니다.", null);
        }
        return host;
    }
}
