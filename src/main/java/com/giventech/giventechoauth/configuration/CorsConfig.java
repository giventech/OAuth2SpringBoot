package com.giventech.giventechoauth.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Import({SecurityConfig.class, MethodSecurityConfig.class})
public class CorsConfig {

    private String originPermitida = "http://localhost:4200"; // TODO: Configurar para diferentes ambientes

    @Bean
    public FilterRegistrationBean filterForAuthorizedOrigins() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration configAutenticacao = new CorsConfiguration();
        configAutenticacao.setAllowCredentials(true);
        configAutenticacao.addAllowedOrigin(originPermitida);
        configAutenticacao.addAllowedHeader("Authorization");
        configAutenticacao.addAllowedHeader("Content-Type");
        configAutenticacao.addAllowedHeader("Accept");
        configAutenticacao.addAllowedMethod("POST");
        configAutenticacao.addAllowedMethod("GET");
        configAutenticacao.addAllowedMethod("DELETE");
        configAutenticacao.addAllowedMethod("PUT");
        configAutenticacao.addAllowedMethod("OPTIONS");
        configAutenticacao.setMaxAge(3600L);
        // source.registerCorsConfiguration("/oauth/token", configAutenticacao);
        source.registerCorsConfiguration("/**", configAutenticacao); // Global para todas as URLs da aplicação

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

        // below is https://github.com/spring-projects/spring-security-oauth/issues/938
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
