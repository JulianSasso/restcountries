package eu.fayder.restcountries.infrastructure.persistence.nosql;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryDocumentMongoDbRepository {
    List<CountryDocument> findAll();

    Optional<CountryDocument> findByAlpha2Code(String code);

    Optional<CountryDocument> findByAlpha3Code(String code);

    List<CountryDocument> findByNameContaining(String countryName);

    List<CountryDocument> findByCallingCode(String code);

    List<CountryDocument> findByCapitalContaining(String partialCapital);

    List<CountryDocument> findByRegion(String region);

    List<CountryDocument> findBySubregion(String subregion);

    List<CountryDocument> findByCurrency(String currency);

    List<CountryDocument> findByLanguageTwoLetterIsoCode(String language);

    List<CountryDocument> findByLanguageThreeLetterIsoCode(String language);

    List<CountryDocument> findByDemonym(String demonym);

    List<CountryDocument> findByRegionalBloc(String regionalBloc);
}
