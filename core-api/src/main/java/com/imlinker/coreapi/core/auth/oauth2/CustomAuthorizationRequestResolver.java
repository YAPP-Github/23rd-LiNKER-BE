package com.imlinker.coreapi.core.auth.oauth2;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final CustomClientOriginHostCache clientOriginHostCache;
    private final DefaultOAuth2AuthorizationRequestResolver defaultResolver;

    public CustomAuthorizationRequestResolver(
            CustomClientOriginHostCache clientOriginHostCache,
            ClientRegistrationRepository clientRegistrationRepository) {
        this.clientOriginHostCache = clientOriginHostCache;
        this.defaultResolver =
                new DefaultOAuth2AuthorizationRequestResolver(
                        clientRegistrationRepository, "/oauth2/authorization");
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        OAuth2AuthorizationRequest oAuthRequest = defaultResolver.resolve(request);
        if (oAuthRequest != null) {
            String state = oAuthRequest.getState();
            clientOriginHostCache.putClientOriginHost(state, getClientOriginHost(request));
        }

        return oAuthRequest;
    }

    @Override
    public OAuth2AuthorizationRequest resolve(
            HttpServletRequest request, String clientRegistrationId) {
        OAuth2AuthorizationRequest oAuthRequest =
                defaultResolver.resolve(request, clientRegistrationId);
        if (oAuthRequest != null) {
            String state = oAuthRequest.getState();
            clientOriginHostCache.putClientOriginHost(state, getClientOriginHost(request));
        }

        return oAuthRequest;
    }

    private String getClientOriginHost(HttpServletRequest request) {
        String origin = request.getHeader("Referer");

        if (origin == null) {
            return "http://localhost:3000";
        }
        return origin;
    }
}
