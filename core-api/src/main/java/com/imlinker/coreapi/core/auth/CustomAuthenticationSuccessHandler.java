package com.imlinker.coreapi.core.auth;

import com.imlinker.coreapi.core.auth.vendor.OAuthVendor;
import com.imlinker.coreapi.core.auth.vendor.OAuthVendorAttributeResolver;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final Map<OAuthVendor, OAuthVendorAttributeResolver> vendorAttributeResolverRegistry;

    public CustomAuthenticationSuccessHandler(
            List<OAuthVendorAttributeResolver> vendorAttributeResolvers) {
        this.vendorAttributeResolverRegistry =
                vendorAttributeResolvers.stream()
                        .collect(
                                Collectors.toMap(OAuthVendorAttributeResolver::getVendor, resolver -> resolver));
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        if (!(authentication.getPrincipal() instanceof OAuth2User)) {
            throw new ApplicationException(ErrorType.INTERNAL_PROCESSING_ERROR);
        }
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
