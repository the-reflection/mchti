package org.reflection.config;

import javax.sql.DataSource;
import org.reflection.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        return customAuthenticationSuccessHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/index", "/login", "/zxLookup/**").permitAll()
                .antMatchers("/homeSecure", "/logout").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')")
                .antMatchers("/edit-user-*").access("hasRole('ADMIN') or hasRole('DBA')")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                //.defaultSuccessUrl("/homeSecure", false)
                .usernameParameter("username")
                .passwordParameter("password").successHandler(authenticationSuccessHandler)
                .and()
                //.logout()
                //.permitAll()
                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and()
                .rememberMe().rememberMeParameter("rememberMe").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)
                .and()
                .csrf()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");

//    http.rememberMe(). 
//                key("rem-me-key").
//                rememberMeParameter("remember-me-param").
//                rememberMeCookieName("my-remember-me").
//                tokenValiditySeconds(86400);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
}
