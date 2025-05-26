package eu.fayder.restcountries.infrastructure.persistence.file.json;

import eu.fayder.restcountries.domain.country.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileCountryRepositoryTest {

    @Mock
    private CountryJsonRepository jsonRepository;

    @Mock
    private CountryJsonMapper mapper;

    @InjectMocks
    private FileCountryRepository fileCountryRepository;

    @Test
    void givenCountries_whenFindAll_thenReturnListOfCountries() {
        // Given
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findAll()).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findAll();

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenAlpha2Code_whenFindByAlpha2Code_thenReturnOptionalCountry() {
        // Given
        String alpha2Code = "US";
        CountryJson countryJson = mock(CountryJson.class);
        Country country = mock(Country.class);

        when(jsonRepository.findByAlpha2Code(alpha2Code)).thenReturn(Optional.of(countryJson));
        when(mapper.toDomain(countryJson)).thenReturn(country);

        // When
        Optional<Country> result = fileCountryRepository.findByAlpha2Code(alpha2Code);

        // Then
        assertTrue(result.isPresent());
        assertEquals(country, result.get());
    }

    @Test
    void givenNonExistentAlpha2Code_whenFindByAlpha2Code_thenReturnEmptyOptional() {
        // Given
        String alpha2Code = "XX";

        when(jsonRepository.findByAlpha2Code(alpha2Code)).thenReturn(Optional.empty());

        // When
        Optional<Country> result = fileCountryRepository.findByAlpha2Code(alpha2Code);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void givenAlpha3Code_whenFindByAlpha3Code_thenReturnOptionalCountry() {
        // Given
        String alpha3Code = "USA";
        CountryJson countryJson = mock(CountryJson.class);
        Country country = mock(Country.class);

        when(jsonRepository.findByAlpha3Code(alpha3Code)).thenReturn(Optional.of(countryJson));
        when(mapper.toDomain(countryJson)).thenReturn(country);

        // When
        Optional<Country> result = fileCountryRepository.findByAlpha3Code(alpha3Code);

        // Then
        assertTrue(result.isPresent());
        assertEquals(country, result.get());
    }

    @Test
    void givenNonExistentAlpha3Code_whenFindByAlpha3Code_thenReturnEmptyOptional() {
        // Given
        String alpha3Code = "XXX";

        when(jsonRepository.findByAlpha3Code(alpha3Code)).thenReturn(Optional.empty());

        // When
        Optional<Country> result = fileCountryRepository.findByAlpha3Code(alpha3Code);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void givenCountryName_whenFindByNameContaining_thenReturnListOfMatchingCountries() {
        // Given
        String countryName = "United";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findByNameContaining(countryName)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findByNameContaining(countryName);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenCallingCode_whenFindByCallingCode_thenReturnListOfMatchingCountries() {
        // Given
        String callingCode = "1";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findByCallingCode(callingCode)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findByCallingCode(callingCode);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenPartialCapital_whenFindByCapitalContaining_thenReturnListOfMatchingCountries() {
        // Given
        String partialCapital = "London";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findByCapitalContaining(partialCapital)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findByCapitalContaining(partialCapital);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenRegion_whenFindByRegion_thenReturnListOfMatchingCountries() {
        // Given
        String region = "Europe";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findByRegion(region)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findByRegion(region);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenSubregion_whenFindBySubregion_thenReturnListOfMatchingCountries() {
        // Given
        String subregion = "Western Europe";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findBySubregion(subregion)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findBySubregion(subregion);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenCurrency_whenFindByCurrency_thenReturnListOfMatchingCountries() {
        // Given
        String currency = "EUR";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findByCurrency(currency)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findByCurrency(currency);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenTwoLetterLanguageCode_whenFindByLanguageTwoLetterIsoCode_thenReturnListOfMatchingCountries() {
        // Given
        String languageCode = "en";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findByLanguageTwoLetterIsoCode(languageCode)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findByLanguageTwoLetterIsoCode(languageCode);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenThreeLetterLanguageCode_whenFindByLanguageThreeLetterIsoCode_thenReturnListOfMatchingCountries() {
        // Given
        String languageCode = "eng";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findByLanguageThreeLetterIsoCode(languageCode)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findByLanguageThreeLetterIsoCode(languageCode);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenDemonym_whenFindByDemonym_thenReturnListOfMatchingCountries() {
        // Given
        String demonym = "American";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findByDemonym(demonym)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findByDemonym(demonym);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenRegionalBloc_whenFindByRegionalBloc_thenReturnListOfMatchingCountries() {
        // Given
        String regionalBloc = "EU";
        CountryJson countryJson1 = mock(CountryJson.class);
        CountryJson countryJson2 = mock(CountryJson.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(jsonRepository.findByRegionalBloc(regionalBloc)).thenReturn(List.of(countryJson1, countryJson2));
        when(mapper.toDomain(countryJson1)).thenReturn(country1);
        when(mapper.toDomain(countryJson2)).thenReturn(country2);

        // When
        List<Country> result = fileCountryRepository.findByRegionalBloc(regionalBloc);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }
}