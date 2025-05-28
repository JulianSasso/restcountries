package eu.fayder.restcountries.infrastructure.persistence.file.json;

import eu.fayder.restcountries.domain.CountryRepository;
import eu.fayder.restcountries.domain.country.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;


import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "repository.type", havingValue = "json")
public class FileCountryRepository implements CountryRepository {

    private final CountryJsonRepository jsonRepository;
    private final CountryJsonMapper mapper;

    @Override
    public List<Country> findAll(Integer page, Integer pageSize) {
        return jsonRepository.findAll(page, pageSize).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Country> findByAlpha2Code(String code) {
        return jsonRepository.findByAlpha2Code(code)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Country> findByAlpha3Code(String code) {
        return jsonRepository.findByAlpha3Code(code)
                .map(mapper::toDomain);
    }

    @Override
    public List<Country> findByNameContaining(String countryName) {
        return jsonRepository.findByNameContaining(countryName)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByCallingCode(String code) {
        return jsonRepository.findByCallingCode(code)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByCapitalContaining(String partialCapital) {
        return jsonRepository.findByCapitalContaining(partialCapital)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByRegion(String region) {
        return jsonRepository.findByRegion(region)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findBySubregion(String subregion) {
        return jsonRepository.findBySubregion(subregion)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByCurrency(String currency) {
        return jsonRepository.findByCurrency(currency)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByLanguageTwoLetterIsoCode(String language) {
        return jsonRepository.findByLanguageTwoLetterIsoCode(language)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByLanguageThreeLetterIsoCode(String language) {
        return jsonRepository.findByLanguageThreeLetterIsoCode(language)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByDemonym(String demonym) {
        return jsonRepository.findByDemonym(demonym)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByRegionalBloc(String regionalBloc) {
        return jsonRepository.findByRegionalBloc(regionalBloc)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

}
