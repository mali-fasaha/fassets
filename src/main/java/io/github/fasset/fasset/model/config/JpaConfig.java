package io.github.fasset.fasset.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
//@Configuration
public class JpaConfig {


    /*@Autowired
    @Qualifier("auditorAware")
    private AuditorAware auditorAware;

    @Bean
    public AuditorAware<String> auditorAware() {
        return auditorAware;
    }*/
}
