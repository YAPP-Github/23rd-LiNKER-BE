package com.imlinker.coreapi.core.auth.security.oauth2;

import com.imlinker.coreapi.core.auth.security.jwt.JwtTokenProvider;
import com.imlinker.coreapi.core.auth.security.jwt.TokenType;
import com.imlinker.coreapi.core.auth.security.oauth2.vendor.OAuthVendor;
import com.imlinker.coreapi.core.auth.security.oauth2.vendor.OAuthVendorAttributeResolver;
import com.imlinker.domain.common.Email;
import com.imlinker.domain.user.UserService;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomClientOriginHostCache clientOriginHostCache;
    private final Map<OAuthVendor, OAuthVendorAttributeResolver> vendorAttributeResolverRegistry;

    public CustomAuthenticationSuccessHandler(
            UserService userService,
            JwtTokenProvider jwtTokenProvider,
            CustomClientOriginHostCache clientOriginHostCache,
            List<OAuthVendorAttributeResolver> vendorAttributeResolvers) {

        this.userService = userService;
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

        Email email = resolver.getEmail(oAuth2User.getAttributes());
        boolean isMember = userService.isMember(email);

        if (isMember) {
            String accessToken = jwtTokenProvider.generateToken(email, TokenType.ACCESS_TOKEN);
            String refreshToken = jwtTokenProvider.generateToken(email, TokenType.REFRESH_TOKEN);

            String redirectUri =
                    clientOriginHost
                            + "?"
                            + String.format(
                                    "%s?accessToken=%s&refreshToken=%s", clientOriginHost, accessToken, refreshToken);
            getRedirectStrategy().sendRedirect(request, response, redirectUri);
        } else {
            String oAuthId = resolver.getOAuthId(oAuth2User.getAttributes());
            String nickname = resolver.getNickname(oAuth2User.getAttributes());
            String profileImgUrl = resolver.getProfileImgUrl(oAuth2User.getAttributes());

            String redirectUri =
                    clientOriginHost
                            + "?"
                            + URLEncoder.encode(
                                    String.format(
                                            "oAuthId=%s&nickname=%s&profileImgUrl=%s&email=%s",
                                            oAuthId, nickname, profileImgUrl, email.getValue()),
                                    StandardCharsets.UTF_8);

            getRedirectStrategy().sendRedirect(request, response, redirectUri);
        }
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
