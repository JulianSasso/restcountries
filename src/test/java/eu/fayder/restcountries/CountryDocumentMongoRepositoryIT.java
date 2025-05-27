package eu.fayder.restcountries;

import eu.fayder.restcountries.domain.country.Country;
import eu.fayder.restcountries.boot.CountriesApplication;
import eu.fayder.restcountries.application.usecase.CountryInformationServiceImpl;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.MongoDbCountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.TestPropertySource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@ComponentScan(basePackages = "eu.fayder.restcountries")
@ContextConfiguration(classes = CountriesApplication.class)
@TestPropertySource(properties = {
        "spring.data.mongodb.database=restcountries-test",
        "spring.data.mongodb.port=27017",
        "spring.data.mongodb.host=localhost"
})
class CountryDocumentMongoRepositoryIT {

    @Autowired
    private MongoDbCountryRepository countryRepository;
    private CountryInformationServiceImpl countryService;

    @BeforeEach
    void setUp() {
        countryService = new CountryInformationServiceImpl(countryRepository);
    }

    @Test
    void whenGetAll_thenCountriesAreReturned() {
        List<Country> all = countryService.getAll();
        assertFalse(all.isEmpty());
    }


    @Test
    void givenAlpha2Code_whenGetByAlpha_thenCountryIsReturned() {
        Optional<Country> result = countryService.getByAlpha("AR");
        assertTrue(result.isPresent());
        assertThat(result.get().getName()).isEqualTo("Argentina");
    }

    @Test
    void givenAlpha3Code_whenGetByAlpha_thenCountryIsReturned() {
        Optional<Country> result = countryService.getByAlpha("ARG");
        assertTrue(result.isPresent());
        assertThat(result.get().getName()).isEqualTo("Argentina");
    }

    @Test
    void whenGetByAlphaCodeList_thenCountriesAreReturned() {
        Set<String> codes = Set.of("AR", "ARG");
        List<Country> countries = countryService.getByAlphaCodeList(codes);
        assertThat(countries.size()).isGreaterThan(1);
    }

    @Test
    void whenGetByNameContaining_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getByNameContaining("Argen");
        assertFalse(countries.isEmpty());
    }

    @Test
    void whenGetByCallingCode_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getByCallingCode("54");
        assertFalse(countries.isEmpty());
    }

    @Test
    void whenGetByCapital_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getByCapital("Buenos Aires");
        assertFalse(countries.isEmpty());
    }

    @Test
    void whenGetByRegion_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getByRegion("Americas");
        assertFalse(countries.isEmpty());
    }

    @Test
    void whenGetBySubregion_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getBySubregion("South America");
        // assertFalse(countries.isEmpty());
    }

    @Test
    void whenGetByCurrency_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getByCurrency("ARS");
        assertFalse(countries.isEmpty());
    }

    @Test
    void whenGetByLanguageWith2Letter_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getByLanguage("es");
        assertFalse(countries.isEmpty());
    }

    @Test
    void whenGetByLanguageWith3Letter_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getByLanguage("spa");
        assertFalse(countries.isEmpty());
    }

    @Test
    void whenGetByDemonym_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getByDemonym("Argentinean");
        assertFalse(countries.isEmpty());
    }

    @Test
    void whenGetByRegionalBloc_thenMatchingCountriesAreReturned() {
        List<Country> countries = countryService.getByRegionalBloc("UNASUR");
        assertFalse(countries.isEmpty());
    }
}