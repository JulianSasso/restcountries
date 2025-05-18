package eu.fayder.restcountries.infrastructure.persistence.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.fayder.restcountries.domain.countryinfo.CountryRepository;
import eu.fayder.restcountries.domain.countryinfo.country.Country;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Repository
public class JsonCountryRepository implements CountryRepository {

    private List<Country> countries;
    private static final String JSON_PATH = "countriesV2.json";

    @PostConstruct
    private void init() {
        System.out.println("Loading countries from JSON file: " + JSON_PATH);
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream(JSON_PATH);
            countries = mapper.readValue(is, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error loading countries JSON", e);
        }
    }

    @Override
    public List<Country> findAll() {
        return countries;
    }

    @Override
    public Optional<Country> findByAlpha2Code(String code) {
        return countries.stream()
                .filter(country -> country.getAlpha2Code().equalsIgnoreCase(code))
                .findFirst();
    }

    @Override
    public Optional<Country> findByAlpha3Code(String code) {
        return countries.stream()
                .filter(country -> country.getAlpha3Code().equalsIgnoreCase(code))
                .findFirst();
    }

    @Override
    public List<Country> findByNameContaining(String countryName) {
        String normalizedCountryName = normalize(countryName);
        return countries.stream()
                .filter(country ->
                        containsIgnoreCase(normalize(country.getName()), normalizedCountryName)
                        || country.getAltSpellings().stream()
                                .anyMatch(altSpelling
                                        -> containsIgnoreCase(normalize(altSpelling), normalizedCountryName)))
                .toList();
    }

    @Override
    public List<Country> findByCallingCode(String code) {
        return countries.stream()
                .filter(country -> country.getCallingCodes().contains(code))
                .toList();
    }

    @Override
    public List<Country> findByCapitalContaining(String partialCapital) {
        String normalizedPartialCapital = normalize(partialCapital);
        return countries.stream()
                .filter(country -> containsIgnoreCase(normalize(country.getCapital()), normalizedPartialCapital))
                .toList();
    }

    @Override
    public List<Country> findByRegion(String region) {
        return countries.stream()
                .filter(country -> country.getRegion().equalsIgnoreCase(region))
                .toList();
    }

    @Override
    public List<Country> findBySubregion(String subregion) {
        return countries.stream()
                .filter(country -> country.getSubregion().equalsIgnoreCase(subregion))
                .toList();
    }

    // TODO: Asegurarnos que no llegan NULL a este punto
    @Override
    public List<Country> findByCurrency(String currency) {
        return countries.stream()
                .filter(country -> country.getCurrencies().stream()
                        .anyMatch(curr -> curr.getCode() != null && curr.getCode().equalsIgnoreCase(currency)))
                .toList();
    }

    @Override
    public List<Country> findByLanguageTwoLetterIsoCode(String language) {
        return countries.stream()
                .filter(country -> country.getLanguages().stream()
                        .anyMatch(lang -> lang.getIso639_1() != null && lang.getIso639_1().equalsIgnoreCase(language)))
                .toList();
    }

    @Override
    public List<Country> findByLanguageThreeLetterIsoCode(String language) {
        return countries.stream()
                .filter(country -> country.getLanguages().stream()
                        .anyMatch(lang -> lang.getIso639_2() != null && lang.getIso639_2().equalsIgnoreCase(language)))
                .toList();
    }

    @Override
    public List<Country> findByDemonym(String demonym) {
        String normalizedDemonym = normalize(demonym);
        return countries.stream()
                .filter(country -> normalize(country.getDemonym()).equalsIgnoreCase(normalizedDemonym))
                .toList();
    }

    @Override
    public List<Country> findByRegionalBloc(String regionalBloc) {
        return countries.stream()
                .filter(country -> country.getRegionalBlocs() != null && country.getRegionalBlocs().stream()
                        .anyMatch(bloc -> bloc.isAlternativeName(regionalBloc)))
                .toList();
    }

    private boolean containsIgnoreCase(String str, String searchStr) {
        return str != null && searchStr != null && str.toLowerCase().contains(searchStr.toLowerCase());
    }

    private String normalize(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
