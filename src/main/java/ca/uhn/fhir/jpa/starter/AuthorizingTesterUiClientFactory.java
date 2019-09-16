package ca.uhn.fhir.jpa.starter;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;
import ca.uhn.fhir.rest.client.interceptor.BearerTokenAuthInterceptor;
import ca.uhn.fhir.rest.server.util.ITestingUiClientFactory;
import org.mitre.openid.connect.model.OIDCAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;

import javax.servlet.http.HttpServletRequest;

public class AuthorizingTesterUiClientFactory implements ITestingUiClientFactory {

    @Override
    public IGenericClient newClient(FhirContext theFhirContext, HttpServletRequest theRequest, String theServerBaseUrl) {
        // Create a client

        IGenericClient client = theFhirContext.newRestfulGenericClient(theServerBaseUrl);

        // Register an interceptor which adds credentials
        String token = "";
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof OIDCAuthenticationToken) {
            OIDCAuthenticationToken oidc = (OIDCAuthenticationToken) auth;
            token = oidc.getAccessTokenValue();

        }
        client.registerInterceptor(new BearerTokenAuthInterceptor(token));

        return client;
    }

}