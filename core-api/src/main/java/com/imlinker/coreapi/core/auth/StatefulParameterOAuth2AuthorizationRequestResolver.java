package com.imlinker.coreapi.core.auth;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatefulParameterOAuth2AuthorizationRequestResolver
        implements OAuth2AuthorizationRequestResolver {

    private final ClientRegistrationRepository clientRegistrationRepository;

    private AntPathRequestMatcher authorizationRequestPatternMatcher =
            new AntPathRequestMatcher("/oauth2/authorization/{registrationId}");

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        String clientRegistrationId = resolveClientRegistrationId(request);
        OAuth2ActionType action = getAction(request).orElse(OAuth2ActionType.LOGIN);

        return resolve(request, clientRegistrationId, action);
    }

    @Override
    public OAuth2AuthorizationRequest resolve(
            HttpServletRequest request, String clientRegistrationId) {
        OAuth2ActionType action = getAction(request).orElse(OAuth2ActionType.ACTION);

        return resolve(request, clientRegistrationId, action);
    }

    private OAuth2AuthorizationRequest resolve(
            HttpServletRequest request, String clientRegistrationId, OAuth2ActionType action) {

        log.info(
                "[OAuth2AuthorizationRequestResolver][resolve] (clientRegistrationId= {}, action= {})",
                clientRegistrationId,
                action);
        ClientRegistration clientRegistration =
                clientRegistrationRepository.findByRegistrationId(clientRegistrationId);

        return null;
    }

    private Optional<OAuth2ActionType> getAction(HttpServletRequest request) {
        String action = request.getParameter("action");
        if (action == null) {
            return Optional.empty();
        }
        return Optional.of(OAuth2ActionType.of(action));
    }

    private String resolveClientRegistrationId(HttpServletRequest request) {
        if (!authorizationRequestPatternMatcher.matcher(request).isMatch()) {
            throw new ApplicationException(
                    ErrorType.INTERNAL_PROCESSING_ERROR, "Unsupported oAuth2 Vendor", null);
        }
        return authorizationRequestPatternMatcher.matcher(request).getVariables().get("registrationId");
    }
}
