package eu.fayder.restcountries.infrastructure.persistence.nosql;

import eu.fayder.restcountries.domain.CountryRepository;
import eu.fayder.restcountries.domain.country.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "repository.type", havingValue = "mongodb")
public class DocumentOrientedCountryRepository implements CountryRepository {

    private final CountryDocumentMongoDbRepository mongoRepository;
    private final CountryDocumentMapper mapper;

    @Override
    public List<Country> findAll() {
        return mongoRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Country> findByAlpha2Code(String code) {
        return mongoRepository.findByAlpha2Code(code)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Country> findByAlpha3Code(String code) {
        return mongoRepository.findByAlpha3Code(code)
                .map(mapper::toDomain);
    }

    @Override
    public List<Country> findByNameContaining(String countryName) {
        return mongoRepository.findByNameContaining(countryName)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByCallingCode(String code) {
        return mongoRepository.findByCallingCode(code)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByCapitalContaining(String partialCapital) {
        return mongoRepository.findByCapitalContaining(partialCapital)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByRegion(String region) {
        return mongoRepository.findByRegion(region)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findBySubregion(String subregion) {
        return mongoRepository.findBySubregion(subregion)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByCurrency(String currency) {
        return mongoRepository.findByCurrency(currency)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByLanguageTwoLetterIsoCode(String language) {
        return mongoRepository.findByLanguageTwoLetterIsoCode(language)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByLanguageThreeLetterIsoCode(String language) {
        return mongoRepository.findByLanguageThreeLetterIsoCode(language)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByDemonym(String demonym) {
        return mongoRepository.findByDemonym(demonym)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByRegionalBloc(String regionalBloc) {
        return mongoRepository.findByRegionalBloc(regionalBloc)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
