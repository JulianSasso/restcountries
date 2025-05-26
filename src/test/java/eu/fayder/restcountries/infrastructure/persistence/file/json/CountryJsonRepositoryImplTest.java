package eu.fayder.restcountries.infrastructure.persistence.file.json;

import eu.fayder.restcountries.boot.config.JsonFileProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryJsonRepositoryImplTest {
    @Mock
    private JsonFileProperties jsonFileProperties;

    private CountryJsonRepositoryImpl repository;

    @BeforeEach
    void setUp() throws Exception {
        when(jsonFileProperties.getFilePath()).thenReturn("countriesTest.json");
        repository = new CountryJsonRepositoryImpl(jsonFileProperties);

        Method initMethod = CountryJsonRepositoryImpl.class.getDeclaredMethod("init");
        initMethod.setAccessible(true);
        initMethod.invoke(repository);
    }

    @Test
    void givenCountriesInJson_whenFindAll_thenReturnAllCountries() {
        List<CountryJson> countries = repository.findAll();
        assertEquals(2, countries.size());
    }

    @Test
    void givenAlpha2Code_whenFindByAlpha2Code_thenReturnMatchingCountry() {
        // Given
        String alpha2Code = "AR";

        // When
        Optional<CountryJson> result = repository.findByAlpha2Code(alpha2Code);

        // Then
        assertTrue(result.isPresent());
        assertEquals(alpha2Code, result.get().getAlpha2Code());
    }

    @Test
    void givenAlpha3Code_whenFindByAlpha3Code_thenReturnMatchingCountry() {
        // Given
        String alpha3Code = "ARG";

        // When
        Optional<CountryJson> result = repository.findByAlpha3Code(alpha3Code);

        // Then
        assertTrue(result.isPresent());
        assertEquals(alpha3Code, result.get().getAlpha3Code());
    }

    @Test
    void givenPartialName_whenFindByNameContaining_thenReturnMatchingCountries() {
        // Given
        String partialName = "Arg";

        // When
        List<CountryJson> result = repository.findByNameContaining(partialName);

        // Then
        assertEquals(1, result.size());
        assertEquals("Argentina", result.get(0).getName());
    }

    @Test
    void givenCallingCode_whenFindByCallingCode_thenReturnMatchingCountry() {
        // Given
        String callingCode = "54";

        // When
        List<CountryJson> result = repository.findByCallingCode(callingCode);

        // Then
        assertEquals(1, result.size());
        assertEquals("Argentina", result.get(0).getName());
    }

    @Test
    void givenPartialCapital_whenFindByCapitalContaining_thenReturnMatchingCountries() {
        // Given
        String partialCapital = "Buenos";

        // When
        List<CountryJson> result = repository.findByCapitalContaining(partialCapital);

        // Then
        assertEquals(1, result.size());
        assertEquals("Buenos Aires", result.get(0).getCapital());
    }

    @Test
    void givenRegion_whenFindByRegion_thenReturnMatchingCountries() {
        // Given
        String region = "Americas";

        // When
        List<CountryJson> result = repository.findByRegion(region);

        // Then
        assertTrue(result.size() >= 2);
        assertTrue(result.stream().anyMatch(country -> "Argentina".equals(country.getName())));
        assertTrue(result.stream().anyMatch(country -> "Brazil".equals(country.getName())));
    }

    @Test
    void givenSubregion_whenFindBySubregion_thenReturnMatchingCountries() {
        // Given
        String subregion = "South America";

        // When
        List<CountryJson> result = repository.findBySubregion(subregion);

        // Then
        assertTrue(result.size() >= 2);
        assertTrue(result.stream().anyMatch(country -> "Argentina".equals(country.getName())));
        assertTrue(result.stream().anyMatch(country -> "Brazil".equals(country.getName())));
    }

    @Test
    void givenCurrencyCode_whenFindByCurrency_thenReturnMatchingCountries() {
        // Given
        String currencyCode = "BRL";

        // When
        List<CountryJson> result = repository.findByCurrency(currencyCode);

        // Then
        assertEquals(1, result.size());
        assertEquals("Brazil", result.get(0).getName());
    }

    @Test
    void givenLanguageTwoLetterCode_whenFindByLanguageTwoLetterIsoCode_thenReturnMatchingCountries() {
        // Given
        String languageCode = "es";

        // When
        List<CountryJson> result = repository.findByLanguageTwoLetterIsoCode(languageCode);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(country -> "Argentina".equals(country.getName())));
    }

    @Test
    void givenLanguageThreeLetterCode_whenFindByLanguageThreeLetterIsoCode_thenReturnMatchingCountries() {
        // Given
        String languageCode = "por";

        // When
        List<CountryJson> result = repository.findByLanguageThreeLetterIsoCode(languageCode);

        // Then
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(country -> "Brazil".equals(country.getName())));
    }

    @Test
    void givenDemonym_whenFindByDemonym_thenReturnMatchingCountries() {
        // Given
        String demonym = "Argentinean";

        // When
        List<CountryJson> result = repository.findByDemonym(demonym);

        // Then
        assertEquals(1, result.size());
        assertEquals("ARG", result.get(0).getAlpha3Code());
    }

    @Test
    void givenRegionalBloc_whenFindByRegionalBloc_thenReturnMatchingCountries() {
        // Given
        String regionalBloc = "USAN";

        // When
        List<CountryJson> result = repository.findByRegionalBloc(regionalBloc);

        // Then
        assertTrue(result.size() >= 2);
        assertTrue(result.stream().anyMatch(country -> "ARG".equals(country.getAlpha3Code())));
        assertTrue(result.stream().anyMatch(country -> "BRA".equals(country.getAlpha3Code())));
    }
}
