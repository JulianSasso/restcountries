package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo;

import eu.fayder.restcountries.domain.country.Country;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country.CountryDocument;
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
class MongoDbCountryRepositoryTest {

    @Mock
    private CountryDocumentMongoRepository mongoRepository;

    @Mock
    private CountryDocumentMapper mapper;

    @InjectMocks
    private MongoDbCountryRepository mongoDbCountryRepository;

    @Test
    void givenCountries_whenFindAll_thenReturnListOfCountries() {
        // Given
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country countryMock1 = mock(Country.class);
        Country countryMock2 = mock(Country.class);

        when(mongoRepository.findAll()).thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(countryMock1);
        when(mapper.toDomain(countryDocument2)).thenReturn(countryMock2);

        // When
        List<Country> result = mongoDbCountryRepository.findAll();

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(countryMock1));
        assertTrue(result.contains(countryMock2));
    }

    @Test
    void givenAlpha2Code_whenFindByAlpha2Code_thenReturnMatchingCountry() {
        // Given
        String code = "US";

        CountryDocument countryDocument = mock(CountryDocument.class);

        Country countryMock = mock(Country.class);
        when(countryMock.getName()).thenReturn("United States");

        when(mongoRepository.findByAlpha2CodeIgnoreCase(code)).thenReturn(Optional.of(countryDocument));
        when(mapper.toDomain(countryDocument)).thenReturn(countryMock);

        // When
        Optional<Country> result = mongoDbCountryRepository.findByAlpha2Code(code);

        // Then
        assertTrue(result.isPresent());
        assertEquals("United States", result.get().getName());
    }

    @Test
    void givenAlpha2CodeNotExists_whenFindByAlpha2Code_thenReturnEmptyOptional() {
        // Given
        when(mongoRepository.findByAlpha2CodeIgnoreCase("XX")).thenReturn(Optional.empty());

        // When
        Optional<Country> result = mongoDbCountryRepository.findByAlpha2Code("XX");

        // Then
        assertFalse(result.isPresent());;
    }

    @Test
    void givenAlpha3Code_whenFindByAlpha3Code_thenReturnMatchingCountry() {
        // Given
        CountryDocument countryDocument = mock(CountryDocument.class);
        Country countryMock = mock(Country.class);
        when(countryMock.getName()).thenReturn("United States");

        when(mongoRepository.findByAlpha3CodeIgnoreCase("USA")).thenReturn(Optional.of(countryDocument));
        when(mapper.toDomain(countryDocument)).thenReturn(countryMock);

        // When
        Optional<Country> result = mongoDbCountryRepository.findByAlpha3Code("USA");

        // Then
        assertTrue(result.isPresent());
        assertEquals("United States", result.get().getName());
    }

    @Test
    void givenCountryName_whenFindByNameContaining_thenReturnMatchingCountries() {
        // Given
        String countryName = "United States";
        String normalizedName = "United States";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findByNameOrAltSpellingsNormalized(normalizedName))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findByNameContaining(countryName);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findByNameOrAltSpellingsNormalized(normalizedName);
    }

    @Test
    void givenCallingCode_whenFindByCallingCode_thenReturnMatchingCountries() {
        // Given
        String callingCode = "+1";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findByCallingCode(callingCode))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findByCallingCode(callingCode);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findByCallingCode(callingCode);
    }

    @Test
    void givenCapitalName_whenFindByCapitalContaining_thenReturnMatchingCountries() {
        // Given
        String capitalName = "Washington";
        String normalizedCapital = "Washington";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findByCapitalNormalized(normalizedCapital))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findByCapitalContaining(capitalName);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findByCapitalNormalized(normalizedCapital);
    }

    @Test
    void givenRegion_whenFindByRegion_thenReturnMatchingCountries() {
        // Given
        String region = "Europe";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findByRegion(region))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findByRegion(region);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findByRegion(region);
    }

    @Test
    void givenSubregion_whenFindBySubregion_thenReturnMatchingCountries() {
        // Given
        String subregion = "Western Europe";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findBySubregion(subregion))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findBySubregion(subregion);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findBySubregion(subregion);
    }

    @Test
    void givenCurrency_whenFindByCurrency_thenReturnMatchingCountries() {
        // Given
        String currency = "EUR";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findByCurrency(currency))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findByCurrency(currency);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findByCurrency(currency);
    }

    @Test
    void givenLanguageTwoLetter_whenFindByLanguageTwoLetterIsoCode_thenReturnMatchingCountries() {
        // Given
        String languageCode = "en";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findByLanguageTwoLetterIsoCode(languageCode))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findByLanguageTwoLetterIsoCode(languageCode);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findByLanguageTwoLetterIsoCode(languageCode);
    }

    @Test
    void givenLanguageThreeLetter_whenFindByLanguageThreeLetterIsoCode_thenReturnMatchingCountries() {
        // Given
        String languageCode = "eng";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findByLanguageThreeLetterIsoCode(languageCode))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findByLanguageThreeLetterIsoCode(languageCode);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findByLanguageThreeLetterIsoCode(languageCode);
    }

    @Test
    void givenDemonym_whenFindByDemonym_thenReturnMatchingCountries() {
        // Given
        String demonym = "American";
        String normalizedDemonym = "American";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findByDemonymNormalized(normalizedDemonym))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findByDemonym(demonym);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findByDemonymNormalized(normalizedDemonym);
    }

    @Test
    void givenRegionalBloc_whenFindByRegionalBloc_thenReturnMatchingCountries() {
        // Given
        String regionalBloc = "EU";
        CountryDocument countryDocument1 = mock(CountryDocument.class);
        CountryDocument countryDocument2 = mock(CountryDocument.class);
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);

        when(mongoRepository.findByRegionalBlocFlexible(regionalBloc))
                .thenReturn(List.of(countryDocument1, countryDocument2));
        when(mapper.toDomain(countryDocument1)).thenReturn(country1);
        when(mapper.toDomain(countryDocument2)).thenReturn(country2);

        // When
        List<Country> result = mongoDbCountryRepository.findByRegionalBloc(regionalBloc);

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        verify(mongoRepository).findByRegionalBlocFlexible(regionalBloc);
    }

}
