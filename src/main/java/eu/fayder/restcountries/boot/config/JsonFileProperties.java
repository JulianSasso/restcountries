package eu.fayder.restcountries.boot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "repository.json")
public class JsonFileProperties {

    private String filePath;
}
