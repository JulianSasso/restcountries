package eu.fayder.restcountries.infrastructure.persistence.file.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import eu.fayder.restcountries.boot.config.JsonFileProperties;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "repository.type", havingValue = "json")
public class CountryJsonRepositoryImpl implements CountryJsonRepository {

    private Map<String, CountryJson> countries;
    private final JsonFileProperties properties;

    @PostConstruct
    private void init() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream(properties.getFilePath());
            List<CountryJson> countryList = objectMapper.readValue(is, new TypeReference<>() {});
            countries = countryList.stream()
                .collect(Collectors.toMap(countryJson -> countryJson.getAlpha3Code().toUpperCase(), Function.identity()));
        } catch (Exception e) {
            throw new RuntimeException("Error loading countries JSON", e);
        }
    }

    @Override
    public List<CountryJson> findAll() {
        return countries.values().stream().toList();
    }

    @Override
    public Optional<CountryJson> findByAlpha2Code(String code) {
        return countries.values().stream()
                .filter(country -> country.getAlpha2Code().equalsIgnoreCase(code))
                .findFirst();
    }

    @Override
    public Optional<CountryJson> findByAlpha3Code(String code) {
        return Optional.ofNullable(countries.get(code.toUpperCase()));
    }

    @Override
    public List<CountryJson> findByNameContaining(String countryName) {
        String normalizedCountryName = normalize(countryName);
        return countries.values().stream()
                .filter(country ->
                        containsIgnoreCase(normalize(country.getName()), normalizedCountryName)
                                || country.getAltSpellings().stream()
                                .anyMatch(altSpelling
                                        -> containsIgnoreCase(normalize(altSpelling), normalizedCountryName)))
                .toList();
    }

    @Override
    public List<CountryJson> findByCallingCode(String code) {
        return countries.values().stream()
                .filter(country -> country.getCallingCodes().contains(code))
                .toList();
    }

    @Override
    public List<CountryJson> findByCapitalContaining(String partialCapital) {
        String normalizedPartialCapital = normalize(partialCapital);
        return countries.values().stream()
                .filter(country -> containsIgnoreCase(normalize(country.getCapital()), normalizedPartialCapital))
                .toList();
    }

    @Override
    public List<CountryJson> findByRegion(String region) {
        return countries.values().stream()
                .filter(country -> country.getRegion().equalsIgnoreCase(region))
                .toList();
    }

    @Override
    public List<CountryJson> findBySubregion(String subregion) {
        return countries.values().stream()
                .filter(country -> country.getSubregion().equalsIgnoreCase(subregion))
                .toList();
    }

    @Override
    public List<CountryJson> findByCurrency(String currency) {
        return countries.values().stream()
                .filter(country -> country.getCurrencies().stream()
                        .anyMatch(curr -> curr.getCode() != null && curr.getCode().equalsIgnoreCase(currency)))
                .toList();
    }

    @Override
    public List<CountryJson> findByLanguageTwoLetterIsoCode(String language) {
        return countries.values().stream()
                .filter(country -> country.getLanguages().stream()
                        .anyMatch(lang -> lang.getIso639_1() != null && lang.getIso639_2().equalsIgnoreCase(language)))
                .toList();
    }

    @Override
    public List<CountryJson> findByLanguageThreeLetterIsoCode(String language) {
        return countries.values().stream()
                .filter(country -> country.getLanguages().stream()
                        .anyMatch(lang -> lang.getIso639_1() != null && lang.getIso639_2().equalsIgnoreCase(language)))
                .toList();
    }

    @Override
    public List<CountryJson> findByDemonym(String demonym) {
        String normalizedDemonym = normalize(demonym);
        return countries.values().stream()
                .filter(country -> normalize(country.getDemonym()).equalsIgnoreCase(normalizedDemonym))
                .toList();
    }

    @Override
    public List<CountryJson> findByRegionalBloc(String regionalBloc) {
        return countries.values().stream()
                .filter(country -> country.getRegionalBlocs() != null && country.getRegionalBlocs().stream()
                        .anyMatch(bloc -> bloc.isAlternativeName(regionalBloc)))
                .toList();
    }

    private boolean containsIgnoreCase(String str, String searchStr) {
        return str != null && searchStr != null && str.toLowerCase().contains(searchStr.toLowerCase());
    }

    private String normalize(String string) {
        if (string == null) {
            return null;
        }
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
