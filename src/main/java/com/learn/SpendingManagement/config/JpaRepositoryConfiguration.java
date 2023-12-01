package com.learn.SpendingManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
      basePackages = "com.learn.SpendingManagement.repository"
)
public class JpaRepositoryConfiguration {
}
