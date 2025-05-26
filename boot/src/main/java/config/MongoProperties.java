package config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoProperties {
    private String uri;
    private String database;
    private String collection;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
