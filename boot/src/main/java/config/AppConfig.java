package config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "application.usecase",
        "eu.fayder.restcountries.infrastructure.persistence",
        "eu.fayder.restcountries.api",
})
@EnableConfigurationProperties({
        JsonFileProperties.class
})
public class AppConfig {
}
