package eu.fayder.restcountries.domain;

import eu.fayder.restcountries.domain.country.Country;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CountryInformationService {

    List<Country> getAll();

    Optional<Country> getByAlpha(String alpha);

    List<Country> getByAlphaCodeList(Set<String> codes);

    List<Country> getByNameContaining(String name);

    List<Country> getByCallingCode(String callingCode);

    List<Country> getByCapital(String capital);

    List<Country> getByRegion(String region);

    List<Country> getBySubregion(String subregion);

    List<Country> getByCurrency(String currency);

    List<Country> getByLanguage(String language);

    List<Country> getByDemonym(String demonym);

    List<Country> getByRegionalBloc(String regionalBloc);
}
