package com.cbastian.apicat.resources.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration(proxyBeanMethods = false)
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class OAuth2ResourceServerSecurityConfig extends WebSecurityConfigurerAdapter {

   //@Autowired
   //private CheckAuthTokenFilter checkAuthTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.addFilterAfter(checkAuthTokenFilter, FilterSecurityInterceptor.class);
        http.csrf().disable()
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .antMatchers("/apicat/**").permitAll()
                                .anyRequest().authenticated()

                )

                //.addFilterAfter(new MyFilter(), OAuth2AuthenticationProcessingFilter.class)
                //.addFilterAfter(new MyFilter(), OAuth2AuthenticationProcessingFilter.class) // o BearerTokenAuthenticationFilter.class
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                //.addFilterAfter(new MyFilter(), OAuth2AuthenticationProcessingFilter.class)
                .oauth2ResourceServer(rs -> rs.opaqueToken(Customizer.withDefaults()));
        //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAutehenticationEntryPointHandler();
    }


}
