package com.csllc.config;


import javax.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    // TODO 5 I disabled CSRF so we could do integration testing from an arbitrary client
    // would like to enable it and do integration testing properly (using CSRF tokens)
    
    /**
     * http://docs.spring.io/spring-security/site/docs/4.0.0.RELEASE/reference/htmlsingle/#authorize-requests
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/login", "/content/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Inject
    public AuthenticationManager authenticationManager(AuthenticationProvider provider) throws Exception {
        return new AuthenticationManagerBuilder(new NopPostProcessor())
                .authenticationProvider(provider)
                .build();
    }
    
    
    
    private static class NopPostProcessor implements ObjectPostProcessor<Object> {
        @Override
        public <Object> Object postProcess(Object object) {
            return object;
        }
    }

}