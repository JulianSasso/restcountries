package eu.fayder.restcountries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// FIXME: Cuando intento moverlo a /boot, los test son ignorados.
@SpringBootApplication
public class CountriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }
}
