package config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo")
@EnableMongoAuditing
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final MongoProperties mongoProperties;

    public MongoConfig(MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
    }

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoProperties.getUri());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}