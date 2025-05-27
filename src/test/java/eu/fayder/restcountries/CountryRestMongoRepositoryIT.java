package eu.fayder.restcountries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.fayder.restcountries.api.controller.CountryController;
import eu.fayder.restcountries.boot.CountriesApplication;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.MongoDbCountryRepository;
import eu.fayder.restcountries.testUtils.JsonTestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {CountriesApplication.class, CountryController.class, MongoDbCountryRepository.class})
@TestPropertySource(properties = {
        "repository.type=mongodb",
})
public class CountryRestMongoRepositoryIT extends CountryRestIT {
}
