package eu.fayder.restcountries;

import static org.assertj.core.api.Assertions.assertThat;

import eu.fayder.restcountries.api.controller.CountryController;
import eu.fayder.restcountries.application.usecase.CountryInformationServiceImpl;
import eu.fayder.restcountries.boot.config.AppConfig;
import eu.fayder.restcountries.testUtils.JsonTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT/*,
        classes = {
                CountryController.class,
                CountryInformationServiceImpl.class,
        }*/)
public class CountryRestIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetByAlpha2IsoCode() {
        // Arrange
        String code = "ar";
        String url = "http://localhost:" + port + "/rest/v2/alpha/" + code;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/argentina.json"))).isTrue();
    }

    @Test
    public void testGetByAlpha3IsoCode() {
        // Arrange
        String code = "arg";
        String url = "http://localhost:" + port + "/rest/v2/alpha/" + code;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/argentina.json"))).isTrue();
    }

    @Test
    public void testGetByNameAlternative() {
        // Arrange
        String altName = "eesti";
        String url = "http://localhost:" + port + "/rest/v2/name/" + altName;


        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/estoniaInArray.json"))).isTrue();
    }

    @Test
    public void testGetByNamePartial() {
        // Arrange
        String partialName = "united";
        String fullTextParam = "fullText=false";
        String url = "http://localhost:" + port + "/rest/v2/name/" + partialName + "?" + fullTextParam;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/unitedKeywordMatches.json"))).isTrue();
    }

    @Test
    public void testGetByAlphaIsoCodeList() {
        // Arrange
        String codesParam = "codes=col;no;ee";
        String url = "http://localhost:" + port + "/rest/v2/alpha" + "?" + codesParam;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/alphaCodesMatches.json"))).isTrue();
    }

    @Test
    public void testGetByCurrency() {
        // Arrange
        String currency = "cop";
        String url = "http://localhost:" + port + "/rest/v2/currency/" + currency;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/copCurrencyMatches.json"))).isTrue();
    }

    @Test
    public void testGetByLanguage() {
        // Arrange
        String language = "es";
        String url = "http://localhost:" + port + "/rest/v2/lang/" + language;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/esLanguageMatches.json"))).isTrue();
    }

    @Test
    public void testGetByCapital() {
        // Arrange
        String capital = "tallinn";
        String url = "http://localhost:" + port + "/rest/v2/capital/" + capital;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/tallinnCapitalMatches.json"))).isTrue();
    }

    @Test
    public void testGetByCallingCode() {
        // Arrange
        String code = "372";
        String url = "http://localhost:" + port + "/rest/v2/callingcode/" + code;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/572CallingCodeMatches.json"))).isTrue();
    }

    @Test
    public void testGetByRegionOnlyNames() {
        // Arrange
        String region = "europe";
        String onlyNamesParam = "fields=name";
        String url = "http://localhost:" + port + "/rest/v2/region/" + region + "?" + onlyNamesParam;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/europeRegionMatchesOnlyNames.json"))).isTrue();
    }

    @Test
    public void testGetByRegionalBlocOnlyNames() {
        // Arrange
        String bloc = "eu";
        String onlyNamesParam = "fields=name";
        String url = "http://localhost:" + port + "/rest/v2/regionalbloc/" + bloc + "?" + onlyNamesParam;

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), JsonTestUtils.loadFileContent("expected/euRegionalBlocMatchesOnlyNames.json"))).isTrue();
    }
}
