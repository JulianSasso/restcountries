package eu.fayder.restcountries.infrastructure.persistence.mongodb;

import eu.fayder.restcountries.boot.config.MongoProperties;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.CountryDocument;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.CountryDocumentMongoDbRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CountryDocumentMongoDbRepositoryImplTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private MongoProperties mongoProperties;

    private CountryDocumentMongoDbRepositoryImpl repository;
    private CountryDocument sampleCountry;

    private final String COLLECTION_NAME = "countries";

    @BeforeEach
    void setUp() {
        sampleCountry = new CountryDocument();
        MockitoAnnotations.openMocks(this);
        when(mongoProperties.getCollection()).thenReturn(COLLECTION_NAME);
        repository = new CountryDocumentMongoDbRepositoryImpl(mongoTemplate, mongoProperties);
    }

    @Test
    void givenQuery_whenFindAll_thenReturnListOfCountries() {
        // Given
        CountryDocument sampleCountry2 = new CountryDocument();
        List<CountryDocument> expectedCountries = List.of(sampleCountry, sampleCountry2);

        when(mongoTemplate.findAll(CountryDocument.class, COLLECTION_NAME)).thenReturn(expectedCountries);

        // When
        List<CountryDocument> actualCountries = repository.findAll();

        // Then
        assertNotNull(actualCountries);
        assertEquals(2, actualCountries.size());
        assertEquals(expectedCountries, actualCountries);
        verify(mongoTemplate, times(1)).findAll(CountryDocument.class, COLLECTION_NAME);
    }

    @Test
    void givenAlpha2Code_whenFindByAlpha2Code_thenReturnMatchingCountry() {
        // Given
        String code = "ES";
        when(mongoTemplate.findOne(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(sampleCountry);

        // When
        Optional<CountryDocument> result = repository.findByAlpha2Code(code);

        // Then
        assertTrue(result.isPresent());
        assertEquals(sampleCountry, result.get());
        verify(mongoTemplate).findOne(argThat(query ->
                        query.getQueryObject().toString().contains("alpha2Code")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenAlpha2CodeNotExists_whenFindByAlpha2Code_thenReturnEmptyOptional() {
        // Given
        String code = "XX";
        when(mongoTemplate.findOne(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(null);

        // When
        Optional<CountryDocument> result = repository.findByAlpha2Code(code);

        // Then
        assertFalse(result.isPresent());
        verify(mongoTemplate).findOne(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenAlpha3Code_whenFindByAlpha3Code_thenReturnMatchingCountry() {
        // Given
        String code = "ESP";
        when(mongoTemplate.findOne(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(sampleCountry);

        // When
        Optional<CountryDocument> result = repository.findByAlpha3Code(code);

        // Then
        assertTrue(result.isPresent());
        assertEquals(sampleCountry, result.get());
        verify(mongoTemplate).findOne(argThat(query ->
                        query.getQueryObject().toString().contains("alpha3Code")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenCountryName_whenFindByNameContaining_thenReturnMatchingCountries() {
        // Given
        String name = "Spa";
        List<CountryDocument> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findByNameContaining(name);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenCallingCode_whenFindByCallingCode_thenReturnMatchingCountries() {
        // Given
        String code = "34";
        List<CountryDocument> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findByCallingCode(code);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("callingCodes")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenCapitalName_whenFindByCapitalContaining_thenReturnMatchingCountries() {
        // Given
        String capital = "Mad";
        List<CountryDocument> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findByCapitalContaining(capital);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("capital")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenRegion_whenFindByRegion_thenReturnMatchingCountries() {
        // Given
        String region = "Europe";
        CountryDocument sampleCountry2 = new CountryDocument();
        List<CountryDocument> expectedCountries = List.of(sampleCountry, sampleCountry2);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findByRegion(region);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedCountries, result);
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("region")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenSubregion_whenFindBySubregion_thenReturnMatchingCountries() {
        // Given
        String subregion = "Southern Europe";
        List<CountryDocument> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findBySubregion(subregion);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("subregion")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenCurrency_whenFindByCurrency_thenReturnMatchingCountries() {
        // Given
        String currency = "EUR";
        CountryDocument sampleCountry2 = new CountryDocument();
        List<CountryDocument> expectedCountries = List.of(sampleCountry, sampleCountry2);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findByCurrency(currency);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedCountries, result);
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("currencies.code")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenLanguageTwoLetter_whenFindByLanguageTwoLetterIsoCode_thenReturnMatchingCountries() {
        // Given
        String language = "es";
        List<CountryDocument> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findByLanguageTwoLetterIsoCode(language);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("languages.iso639_1")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenLanguageThreeLetter_whenFindByLanguageThreeLetterIsoCode_thenReturnMatchingCountries() {
        // Given
        String language = "spa";
        List<CountryDocument> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findByLanguageThreeLetterIsoCode(language);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("languages.iso639_2")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenDemonym_whenFindByDemonym_thenReturnMatchingCountries() {
        // Given
        String demonym = "Spanish";
        List<CountryDocument> expectedCountries = List.of(sampleCountry);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findByDemonym(demonym);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCountry, result.get(0));
        verify(mongoTemplate).find(argThat(query ->
                        query.getQueryObject().toString().contains("demonym")),
                eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

    @Test
    void givenRegionalBloc_whenFindByRegionalBloc_thenReturnMatchingCountries() {
        // Given
        String regionalBloc = "EU";
        CountryDocument sampleCountry2 = new CountryDocument();
        List<CountryDocument> expectedCountries = List.of(sampleCountry, sampleCountry2);

        when(mongoTemplate.find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME))).thenReturn(expectedCountries);

        // When
        List<CountryDocument> result = repository.findByRegionalBloc(regionalBloc);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedCountries, result);
        verify(mongoTemplate).find(any(Query.class), eq(CountryDocument.class), eq(COLLECTION_NAME));
    }

}
