package eu.fayder.restcountries;

import static org.assertj.core.api.Assertions.assertThat;

import eu.fayder.restcountries.testUtils.JsonTestUtils;
import eu.fayder.restcountries.testUtils.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryRestIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getByAlpha_shouldReturnArgentina() {
        // Arrange
        String url = "http://localhost:" + port + "/rest/v2/alpha/arg";

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(JsonTestUtils.jsonEquals(response.getBody(), TestData.argentinaJson())).isTrue();
    }
}
