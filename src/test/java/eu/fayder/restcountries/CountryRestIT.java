package eu.fayder.restcountries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.fayder.restcountries.api.controller.CountryController;
import eu.fayder.restcountries.boot.CountriesApplication;
import eu.fayder.restcountries.infrastructure.persistence.file.json.FileCountryRepository;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {CountriesApplication.class, CountryController.class})
public class CountryRestIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetFirst10Countries() throws JsonProcessingException {
        // Arrange
        int pageParam = 1;
        int pageSizeParam = 10;
        String url = "http://localhost:%d/rest/v2/all?page=%d&pageSize=%d".formatted(port, pageParam, pageSizeParam);
        String expectedJson = JsonTestUtils.loadFileContent("expected/10FirstCountries.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for first 10 countries")
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByAlpha2IsoCode() throws JsonProcessingException {
        // Arrange
        String code = "ar";
        String url = "http://localhost:" + port + "/rest/v2/alpha/" + code;
        String expectedJson = JsonTestUtils.loadFileContent("expected/argentina.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for country with alpha2Code '%s'", code.toUpperCase())
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByAlpha3IsoCode() throws JsonProcessingException {
        // Arrange
        String code = "arg";
        String url = "http://localhost:" + port + "/rest/v2/alpha/" + code;
        String expectedJson = JsonTestUtils.loadFileContent("expected/argentina.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for country with alpha3Code '%s'", code.toUpperCase())
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByDemonym() throws JsonProcessingException {
        // Arrange
        String demonym = "Estonian";
        String url = "http://localhost:" + port + "/rest/v2/demonym/" + demonym;
        String expectedJson = JsonTestUtils.loadFileContent("expected/estoniaInArray.json");


        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for country with demonym '%s'", demonym)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByNameAlternative() throws JsonProcessingException {
        // Arrange
        String altName = "eesti";
        String url = "http://localhost:" + port + "/rest/v2/name/" + altName;
        String expectedJson = JsonTestUtils.loadFileContent("expected/estoniaInArray.json");


        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for country with altName '%s'", altName)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByNamePartial() throws JsonProcessingException {
        // Arrange
        String partialName = "united";
        String fullTextParam = "fullText=false";
        String url = "http://localhost:" + port + "/rest/v2/name/" + partialName + "?" + fullTextParam;
        String expectedJson = JsonTestUtils.loadFileContent("expected/unitedKeywordMatches.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for country with partial name '%s'", partialName)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByAlphaIsoCodeList() throws JsonProcessingException {
        // Arrange
        String codesParam = "codes=col;no;ee";
        String url = "http://localhost:" + port + "/rest/v2/alpha" + "?" + codesParam;
        String expectedJson = JsonTestUtils.loadFileContent("expected/alphaCodesMatches.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for countries with alpha codes '%s'", codesParam)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByCurrency() throws JsonProcessingException {
        // Arrange
        String currency = "cop";
        String url = "http://localhost:" + port + "/rest/v2/currency/" + currency;
        String expectedJson = JsonTestUtils.loadFileContent("expected/copCurrencyMatches.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for countries with currency '%s'", currency)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByLanguage() throws JsonProcessingException {
        // Arrange
        String language = "es";
        String url = "http://localhost:" + port + "/rest/v2/lang/" + language;
        String expectedJson = JsonTestUtils.loadFileContent("expected/esLanguageMatches.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for countries with language '%s'", language)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByCapital() throws JsonProcessingException {
        // Arrange
        String capital = "tallinn";
        String url = "http://localhost:" + port + "/rest/v2/capital/" + capital;
        String expectedJson = JsonTestUtils.loadFileContent("expected/tallinnCapitalMatches.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for countries with capital '%s'", capital)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByCallingCode() throws JsonProcessingException {
        // Arrange
        String code = "372";
        String url = "http://localhost:" + port + "/rest/v2/callingcode/" + code;
        String expectedJson = JsonTestUtils.loadFileContent("expected/572CallingCodeMatches.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for countries with calling code '%s'", code)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByRegionOnlyNames() throws JsonProcessingException {
        // Arrange
        String region = "europe";
        String onlyNamesParam = "fields=name";
        String url = "http://localhost:" + port + "/rest/v2/region/" + region + "?" + onlyNamesParam;
        String expectedJson = JsonTestUtils.loadFileContent("expected/europeRegionMatches.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for countries with calling region '%s'", region)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }

    @Test
    public void testGetByRegionalBlocOnlyNames() throws JsonProcessingException {
        // Arrange
        String bloc = "eu";
        String onlyNamesParam = "fields=name";
        String url = "http://localhost:" + port + "/rest/v2/regionalbloc/" + bloc + "?" + onlyNamesParam;
        String expectedJson = JsonTestUtils.loadFileContent("expected/euRegionalBlocMatches.json");

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(parseJsonNode(response.getBody()))
                .as("Comparing fields for countries with regional bloc '%s'", bloc)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("_children.translations._children")
                .isEqualTo(parseJsonNode(expectedJson));
    }


    private JsonNode parseJsonNode(String json) throws JsonProcessingException {
        return objectMapper.readTree(json);
    }
}
