package eu.fayder.restcountries.infrastructure.persistence.json;

import eu.fayder.restcountries.domain.country.Currency;
import eu.fayder.restcountries.domain.country.Language;
import eu.fayder.restcountries.domain.country.RegionalBloc;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CountryJson {
    public String name;
    public List<String> topLevelDomain;
    public String alpha2Code;
    public String alpha3Code;
    public List<String> callingCodes;
    public String capital;
    public List<String> altSpellings;
    public String region;
    public String subregion;
    public Integer population;
    public List<Double> latlng;
    public String demonym;
    public Double area;
    public Double gini;
    public List<String> timezones;
    public List<String> borders;
    public String nativeName;
    public String numericCode;
    public List<Currency> currencies;
    public List<Language> languages;
    public Map<String, String> translations;
    public String flag;
    public List<RegionalBloc> regionalBlocs;
    public String cioc;
}
