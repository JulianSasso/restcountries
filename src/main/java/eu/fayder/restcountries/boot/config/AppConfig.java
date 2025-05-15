package eu.fayder.restcountries.boot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "eu.fayder.restcountries.application.usecase"
})
public class AppConfig {
}
