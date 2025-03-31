package com.athikomvorapat.csv_parser;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.athikomvorapat.csv_parser")
public class PersistenceConfig {

}
