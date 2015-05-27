package com.csllc.config;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;

/**
 * http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#auditing
 * http://stackoverflow.com/questions/29880911/how-to-configure-auditing-via-java-config-in-spring-data-and-spring-data-rest
  */
@EnableJpaAuditing
@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<String> createAuditorProvider() {
        return new SpringSecurityAuditorProvider();
    }

    public static class SpringSecurityAuditorProvider implements AuditorAware<String> {
        @Override
        public String getCurrentAuditor() {
            Authentication auth = getContext().getAuthentication();
            String username = auth.getName();
            return username;
        }
    }

}
