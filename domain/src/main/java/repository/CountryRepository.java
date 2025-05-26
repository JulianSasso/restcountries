package repository;

import country.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {

    List<Country> findAll();

    Optional<Country> findByAlpha2Code(String code);

    Optional<Country> findByAlpha3Code(String code);

    List<Country> findByNameContaining(String countryName);

    List<Country> findByCallingCode(String code);

    List<Country> findByCapitalContaining(String partialCapital);

    List<Country> findByRegion(String region);

    List<Country> findBySubregion(String subregion);

    List<Country> findByCurrency(String currency);

    List<Country> findByLanguageTwoLetterIsoCode(String language);

    List<Country> findByLanguageThreeLetterIsoCode(String language);

    List<Country> findByDemonym(String demonym);

    List<Country> findByRegionalBloc(String regionalBloc);

}
