package com.imlinker.coreapi.core.auth;

import com.imlinker.coreapi.core.auth.vendor.OAuthVendor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest: {}", userRequest);

        ClientRegistration registration = userRequest.getClientRegistration();
        OAuth2User user = new DefaultOAuth2UserService().loadUser(userRequest);

        return new CustomOAuth2User(OAuthVendor.of(registration.getRegistrationId()), user);
    }
}
