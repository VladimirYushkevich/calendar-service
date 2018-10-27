package com.outfittery.calendar;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.outfittery.calendar.models"})
@EnableJpaRepositories(basePackages = {"com.outfittery.calendar.repositories"})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RepositoryConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("any"); //we don't need information about who created/modified JPA entity
    }
}
