package eu.fayder.restcountries;

import eu.fayder.restcountries.api.controller.CountryController;
import eu.fayder.restcountries.boot.CountriesApplication;
import eu.fayder.restcountries.infrastructure.persistence.file.json.FileCountryRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {CountriesApplication.class, CountryController.class, FileCountryRepository.class})
@TestPropertySource(properties = {
        "repository.type=json",
        "repository.json.file-path=countriesV2.json",
})
public class CountryRestFileRepositoryIT extends CountryRestIT {
}
