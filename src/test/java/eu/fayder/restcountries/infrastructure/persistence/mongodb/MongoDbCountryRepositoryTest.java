package eu.fayder.restcountries.infrastructure.persistence.mongodb;

import eu.fayder.restcountries.boot.config.MongoProperties;
import eu.fayder.restcountries.domain.country.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoDbCountryRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private MongoProperties mongoProperties;

    private MongoDbCountryRepository repository;
    private Country sampleCountry;

    private final String COLLECTION_NAME = "countries";

    @BeforeEach
    void setUp() {
        sampleCountry = new Country();
        MockitoAnnotations.openMocks(this);
        when(mongoProperties.getCollection()).thenReturn(COLLECTION_NAME);
        repository = new MongoDbCountryRepository(mongoTemplate, mongoProperties);
    }

    @Test
    void givenQuery_whenFindAll_thenReturnListOfCountries() {
        // Given
        Country sampleCountry2 = new Country();
        List<Country> expectedCountries = List.of(sampleCountry, sampleCountry2);

        when(mongoTemplate.findAll(Country.class, COLLECTION_NAME)).thenReturn(expectedCountries);

        // When
        List<Country> actualCountries = repository.findAll();

        // Then
        assertNotNull(actualCountries);
        assertEquals(2, actualCountries.size());
        assertEquals(expectedCountries, actualCountries);
        verify(mongoTemplate, times(1)).findAll(Country.class, COLLECTION_NAME);
    }

    @Test
    void givenAlpha2Code_whenFindByAlpha2Code_thenReturnMatchingCountry() {
        // Given
        String code = "ES";
        when(mongoTemplate.findOne(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(sampleCountry);

        // When
        Optional<Country> result = repository.findByAlpha2Code(code);

        // Then
        assertTrue(result.isPresent());
        assertEquals(sampleCountry, result.get());
        verify(mongoTemplate).findOne(argThat(query ->
                        query.getQueryObject().toString().contains("alpha2Code")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenAlpha2CodeNotExists_whenFindByAlpha2Code_thenReturnEmptyOptional() {
        // Given
        String code = "XX";
        when(mongoTemplate.findOne(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(null);

        // When
        Optional<Country> result = repository.findByAlpha2Code(code);

        // Then
        assertFalse(result.isPresent());
        verify(mongoTemplate).findOne(any(Query.class), eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenAlpha3Code_whenFindByAlpha3Code_thenReturnMatchingCountry() {
        // Given
        String code = "ESP";
        when(mongoTemplate.findOne(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(sampleCountry);

        // When
        Optional<Country> result = repository.findByAlpha3Code(code);

        // Then
        assertTrue(result.isPresent());
        assertEquals(sampleCountry, result.get());
        verify(mongoTemplate).findOne(argThat(query ->
                        query.getQueryObject().toString().contains("alpha3Code")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenCountryName_whenFindByNameContaining_thenReturnMatchingCountries() {
        // Given
        String name = "Spa";
        List<Country> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findByNameContaining(name);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenCallingCode_whenFindByCallingCode_thenReturnMatchingCountries() {
        // Given
        String code = "34";
        List<Country> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findByCallingCode(code);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("callingCodes")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenCapitalName_whenFindByCapitalContaining_thenReturnMatchingCountries() {
        // Given
        String capital = "Mad";
        List<Country> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findByCapitalContaining(capital);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("capital")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenRegion_whenFindByRegion_thenReturnMatchingCountries() {
        // Given
        String region = "Europe";
        Country sampleCountry2 = new Country();
        List<Country> expectedCountries = List.of(sampleCountry, sampleCountry2);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findByRegion(region);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedCountries, result);
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("region")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenSubregion_whenFindBySubregion_thenReturnMatchingCountries() {
        // Given
        String subregion = "Southern Europe";
        List<Country> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findBySubregion(subregion);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("subregion")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenCurrency_whenFindByCurrency_thenReturnMatchingCountries() {
        // Given
        String currency = "EUR";
        Country sampleCountry2 = new Country();
        List<Country> expectedCountries = List.of(sampleCountry, sampleCountry2);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findByCurrency(currency);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedCountries, result);
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("currencies.code")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenLanguageTwoLetter_whenFindByLanguageTwoLetterIsoCode_thenReturnMatchingCountries() {
        // Given
        String language = "es";
        List<Country> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findByLanguageTwoLetterIsoCode(language);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("languages.iso639_1")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenLanguageThreeLetter_whenFindByLanguageThreeLetterIsoCode_thenReturnMatchingCountries() {
        // Given
        String language = "spa";
        List<Country> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findByLanguageThreeLetterIsoCode(language);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("languages.iso639_2")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenDemonym_whenFindByDemonym_thenReturnMatchingCountries() {
        // Given
        String demonym = "Spanish";
        List<Country> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findByDemonym(demonym);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("demonym")),
                eq(Country.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenRegionalBloc_whenFindByRegionalBloc_thenReturnMatchingCountries() {
        // Given
        String regionalBloc = "EU";
        Country sampleCountry2 = new Country();
        List<Country> expectedCountries = List.of(sampleCountry, sampleCountry2);

        when(mongoTemplate.find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<Country> result = repository.findByRegionalBloc(regionalBloc);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedCountries, result);
        verify(mongoTemplate).find(any(Query.class), eq(Country.class), eq(COLLECTION_NAME));
    }

}
