package com.giventech.giventechoauth.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration   extends AuthorizationServerConfigurerAdapter {

    //Spring security Authentication relies
    /** FilterChain:  a range of filter (e.g. based on URL etc
     *  Authentication manager a pure interface to Authenticate
     *  Authentication provider: definiing where authentication data is stored
     */

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;
    //Authentication interface performs the Authentication via Authenticate
    //It then Delegates to  with an Authentication provider with check storage such as
    //JDBC, LDAP or any other type


    @Bean
    public TokenStore tokenStore() {
           return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authManager);
    }




    private static final String CLIENT_ID = "clientapp";
    // encoding method prefix is required for DelegatingPasswordEncoder which is default since Spring Security 5.0.0.RC1
    // you can use one of bcrypt/noop/pbkdf2/scrypt/sha256
    // you can change default behaviour by providing a bean with the encoder you want
    // more: https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
    static final String CLIENT_SECRET = "123456";

    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final String READ_MY_PROFILE_INFO = "read_profile_info";


    private static final String TRUST = "trust";
    private static final int VALID_FOREVER = -1;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory() // It uses an in memory database to store Authentication details
                //
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                // This provide the different pathways according to OAuth2 Specification
                /**
                 * authorisation_code:  end-user providing an system(entity) the ability to access resources from another  partnerentity
                 * password:
                 * client_credential: "For service to service authentication (e.g. Microservices)
                 * https://docs.pivotal.io/p-identity/1-11/grant-types.html
                 */
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN)
                .authorities("READ_ONLY_CLIENT")
                // .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .scopes(READ_MY_PROFILE_INFO)
                .resourceIds("oauth2-resource")
                .redirectUris("http://localhost:8080/login")
                .accessTokenValiditySeconds(VALID_FOREVER)
                .refreshTokenValiditySeconds(VALID_FOREVER);


    }
}
