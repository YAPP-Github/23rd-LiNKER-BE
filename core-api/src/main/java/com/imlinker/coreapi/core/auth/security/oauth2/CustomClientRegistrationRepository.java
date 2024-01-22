package com.imlinker.coreapi.core.auth.security.oauth2;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesMapper;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomClientRegistrationRepository implements ClientRegistrationRepository {
    private final InMemoryClientRegistrationRepository inMemoryClientRegistrationRepository;

    public CustomClientRegistrationRepository(OAuth2ClientProperties oAuth2ClientProperties) {
        OAuth2ClientPropertiesMapper mapper = new OAuth2ClientPropertiesMapper(oAuth2ClientProperties);
        inMemoryClientRegistrationRepository =
                new InMemoryClientRegistrationRepository(mapper.asClientRegistrations());
    }

    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        ClientRegistration registration =
                inMemoryClientRegistrationRepository.findByRegistrationId(registrationId);
        if (registration == null) {
            throw new ApplicationException(
                    ErrorType.INTERNAL_PROCESSING_ERROR, "Unsupported oAuth2 Vendor", null);
        }

        return registration;
    }
}
