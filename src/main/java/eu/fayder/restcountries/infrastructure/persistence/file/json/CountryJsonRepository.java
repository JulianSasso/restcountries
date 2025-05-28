package eu.fayder.restcountries.infrastructure.persistence.file.json;

import eu.fayder.restcountries.infrastructure.persistence.file.json.country.CountryJson;

import java.util.List;
import java.util.Optional;

public interface CountryJsonRepository {

    default List<CountryJson> findAll(){
        return findAll(null, null);
    }

    List<CountryJson> findAll(Integer page, Integer pageSize);

    Optional<CountryJson> findByAlpha2Code(String code);

    Optional<CountryJson> findByAlpha3Code(String code);

    List<CountryJson> findByNameContaining(String countryName);

    List<CountryJson> findByCallingCode(String code);

    List<CountryJson> findByCapitalContaining(String partialCapital);

    List<CountryJson> findByRegion(String region);

    List<CountryJson> findBySubregion(String subregion);

    // TODO: Asegurarnos que no llegan NULL a este punto
    List<CountryJson> findByCurrency(String currency);

    List<CountryJson> findByLanguageTwoLetterIsoCode(String language);

    List<CountryJson> findByLanguageThreeLetterIsoCode(String language);

    List<CountryJson> findByDemonym(String demonym);

    List<CountryJson> findByRegionalBloc(String regionalBloc);
}
