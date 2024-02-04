package com.imlinker.coreapi.core.auth.security.oauth2;

import com.imlinker.coreapi.core.auth.security.jwt.JwtTokenProvider;
import com.imlinker.coreapi.core.auth.security.jwt.TokenType;
import com.imlinker.coreapi.core.auth.security.oauth2.vendor.OAuthVendorAttributeResolver;
import com.imlinker.domain.auth.AuthService;
import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.user.model.User;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value(value = "${client.oauth-callback-url}")
    private String clientRedirectUrl;

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomClientOriginHostCache clientOriginHostCache;
    private final Map<OAuthVendor, OAuthVendorAttributeResolver> vendorAttributeResolverRegistry;

    public CustomAuthenticationSuccessHandler(
            AuthService authService,
            JwtTokenProvider jwtTokenProvider,
            CustomClientOriginHostCache clientOriginHostCache,
            List<OAuthVendorAttributeResolver> vendorAttributeResolvers) {

        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.clientOriginHostCache = clientOriginHostCache;
        this.vendorAttributeResolverRegistry =
                vendorAttributeResolvers.stream()
                        .collect(
                                Collectors.toMap(OAuthVendorAttributeResolver::getVendor, resolver -> resolver));
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        if (!(authentication.getPrincipal() instanceof CustomOAuth2User oAuth2User)) {
            throw new ApplicationException(ErrorType.INTERNAL_PROCESSING_ERROR);
        }

        String clientOriginHost = getHostFromCache(request.getParameter("state"));
        OAuthVendorAttributeResolver resolver =
                vendorAttributeResolverRegistry.get(oAuth2User.getVendor());

        String oAuthId = resolver.getOAuthId(oAuth2User.getAttributes());
        Email email = resolver.getEmail(oAuth2User.getAttributes());

        boolean isMember = authService.isMember(oAuth2User.getVendor(), oAuthId);

        if (!isMember) {
            String nickname = resolver.getNickname(oAuth2User.getAttributes());
            URL profileImgUrl = URL.of(resolver.getProfileImgUrl(oAuth2User.getAttributes()));

            authService.create(oAuthId, nickname, email, profileImgUrl, oAuth2User.getVendor());
        }

        User user = authService.findByOAuthInfo(oAuth2User.getVendor(), oAuthId);
        String accessToken =
                jwtTokenProvider.generateToken(user.id(), user.email(), TokenType.ACCESS_TOKEN);
        String refreshToken =
                jwtTokenProvider.generateToken(user.id(), user.email(), TokenType.REFRESH_TOKEN);

        authService.updateRefreshToken(user.id(), refreshToken);

        String redirectUri =
                String.format(
                        "%s%s?accessToken=%s&refreshToken=%s",
                        clientOriginHost,clientRedirectUrl, accessToken, refreshToken);
        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }

    private String getHostFromCache(String state) {
        String host = clientOriginHostCache.getClientOriginHost(state);
        if (host == null) {
            throw new ApplicationException(
                    ErrorType.INTERNAL_PROCESSING_ERROR, "ClientHost를 찾을 수 없습니다.", null);
        }
        clientOriginHostCache.deleteClientOriginHost(state);
        return host;
    }
}
