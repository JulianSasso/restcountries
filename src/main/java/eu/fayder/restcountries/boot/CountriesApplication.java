package eu.fayder.restcountries.boot;

import eu.fayder.restcountries.boot.config.JsonFileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class CountriesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }
}
