package eu.fayder.restcountries.application.usecase;

import eu.fayder.restcountries.domain.CountryRepository;
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
class CountryInformationServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryInformationServiceImpl countryService;

    @Test
    void givenQuery_whenFindAll_thenReturnListOfCountries() {
        // given
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);
        when(countryRepository.findAll()).thenReturn(List.of(country1, country2));

        // when
        List<Country> result = countryService.getAll();

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
    }

    @Test
    void givenAlpha2Code_whenFindByAlpha_thenReturnCountry() {
        // given
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByAlpha2Code("AR")).thenReturn(Optional.of(sampleCountry));

        // when
        Optional<Country> result = countryService.getByAlpha("AR");

        // then
        assertTrue(result.isPresent());
        assertSame(sampleCountry, result.get());
    }

    @Test
    void givenAlpha3Code_whenFindByAlpha_thenReturnCountry() {
        // given
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByAlpha3Code("ARG")).thenReturn(Optional.of(sampleCountry));

        // when
        Optional<Country> result = countryService.getByAlpha("ARG");

        // then
        assertTrue(result.isPresent());
        assertSame(sampleCountry, result.get());
    }

    @Test
    void givenInvalidAlphaCode_whenFindByAlpha_thenReturnEmpty() {
        // when
        Optional<Country> result = countryService.getByAlpha("XYZW");

        // then
        assertTrue(result.isEmpty());
        verifyNoInteractions(countryRepository);
    }

    @Test
    void givenName_whenGetByNameContaining_thenReturnCountries() {
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByNameContaining("Arg")).thenReturn(List.of(sampleCountry));

        List<Country> result = countryService.getByNameContaining("Arg");

        assertEquals(1, result.size());
        assertSame(sampleCountry, result.get(0));
    }

    @Test
    void givenCode_whenGetByCallingCode_thenReturnCountries() {
        Country c = mock(Country.class);
        when(countryRepository.findByCallingCode("54")).thenReturn(List.of(c));

        List<Country> result = countryService.getByCallingCode("54");

        assertEquals(1, result.size());
        assertSame(c, result.get(0));
    }

    @Test
    void givenCapital_whenGetByCapital_thenReturnCountries() {
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByCapitalContaining("Buenos")).thenReturn(List.of(sampleCountry));

        List<Country> result = countryService.getByCapital("Buenos");

        assertEquals(1, result.size());
        assertSame(sampleCountry, result.get(0));
    }

    @Test
    void givenRegion_whenGetByRegion_thenReturnCountries() {
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByRegion("Americas")).thenReturn(List.of(sampleCountry));

        List<Country> result = countryService.getByRegion("Americas");

        assertEquals(1, result.size());
        assertSame(sampleCountry, result.get(0));
    }

    @Test
    void givenSubregion_whenGetBySubregion_thenReturnCountries() {
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findBySubregion("South America")).thenReturn(List.of(sampleCountry));

        List<Country> result = countryService.getBySubregion("South America");

        assertEquals(1, result.size());
        assertSame(sampleCountry, result.get(0));
    }

    @Test
    void givenCurrency_whenGetByCurrency_thenReturnCountries() {
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByCurrency("ARS")).thenReturn(List.of(sampleCountry));

        List<Country> result = countryService.getByCurrency("ARS");

        assertEquals(1, result.size());
        assertSame(sampleCountry, result.get(0));
    }

    @Test
    void given2LetterLanguage_whenGetByLanguage_thenReturnCountries() {
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByLanguageTwoLetterIsoCode("es")).thenReturn(List.of(sampleCountry));

        List<Country> result = countryService.getByLanguage("es");

        assertEquals(1, result.size());
        assertSame(sampleCountry, result.get(0));
    }

    @Test
    void given3LetterLanguage_whenGetByLanguage_thenReturnCountries() {
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByLanguageThreeLetterIsoCode("spa")).thenReturn(List.of(sampleCountry));

        List<Country> result = countryService.getByLanguage("spa");

        assertEquals(1, result.size());
        assertSame(sampleCountry, result.get(0));
    }

    @Test
    void givenInvalidLanguageCode_whenGetByLanguage_thenReturnEmptyList() {
        List<Country> result = countryService.getByLanguage("spanish");

        assertTrue(result.isEmpty());
        verifyNoInteractions(countryRepository);
    }

    @Test
    void givenDemonym_whenGetByDemonym_thenReturnCountries() {
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByDemonym("Argentine")).thenReturn(List.of(sampleCountry));

        List<Country> result = countryService.getByDemonym("Argentine");

        assertEquals(1, result.size());
        assertSame(sampleCountry, result.get(0));
    }

    @Test
    void givenBloc_whenGetByRegionalBloc_thenReturnCountries() {
        Country sampleCountry = mock(Country.class);
        when(countryRepository.findByRegionalBloc("UNASUR")).thenReturn(List.of(sampleCountry));

        List<Country> result = countryService.getByRegionalBloc("UNASUR");

        assertEquals(1, result.size());
        assertSame(sampleCountry, result.get(0));
    }
}