package eu.fayder.restcountries.domain.countryinfo;

import eu.fayder.restcountries.domain.countryinfo.country.BaseCountry;
import eu.fayder.restcountries.domain.countryinfo.country.Country;

import java.util.List;

public interface CountryInformationService {

    List<Country> getAll();

    Country getByAlpha(String alpha);

    List<Country> getByCodeList(String codeList);

    List<Country> getByName(String name, boolean fullText);

    List<Country> getByCallingCode(String callingCode);

    List<Country> getByCapital(String capital);

    List<Country> getByRegion(String region);

    List<Country> getBySubregion(String subregion);

    List<Country> getByCurrency(String currency);

    List<Country> getByLanguage(String language);

    List<Country> getByDemonym(String demonym);

    List<Country> getByRegionalBloc(String regionalBloc);
}
