package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo;

import eu.fayder.restcountries.domain.CountryRepository;
import eu.fayder.restcountries.domain.country.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "repository.type", havingValue = "mongodb")
public class DocumentOrientedCountryRepository implements CountryRepository {

    private final FinalCountryDocumentMongoRepository mongoRepository;
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
        return mongoRepository.findByAlpha2CodeIgnoreCase(code)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Country> findByAlpha3Code(String code) {
        return mongoRepository.findByAlpha3CodeIgnoreCase(code)
                .map(mapper::toDomain);
    }

    @Override
    public List<Country> findByNameContaining(String countryName) {
        String normalizedCountryName = normalize(countryName);
        return mongoRepository.findByNameOrAltSpellingsNormalized(normalizedCountryName)
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
        String normalizedCapital = normalize(partialCapital);
        return mongoRepository.findByCapitalNormalized(normalizedCapital)
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
        String normalizedDemonym = normalize(demonym);
        return mongoRepository.findByDemonymNormalized(normalizedDemonym)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Country> findByRegionalBloc(String regionalBloc) {
        return mongoRepository.findByRegionalBlocFlexible(regionalBloc)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }


    private String normalize(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
