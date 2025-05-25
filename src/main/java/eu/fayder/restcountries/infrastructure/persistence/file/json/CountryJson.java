package eu.fayder.restcountries.infrastructure.persistence.file.json;

import eu.fayder.restcountries.domain.country.Currency;
import eu.fayder.restcountries.domain.country.Language;
import eu.fayder.restcountries.domain.country.RegionalBloc;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class CountryJson {
    private String name;
    private List<String> topLevelDomain;
    private String alpha2Code;
    private String alpha3Code;
    private List<String> callingCodes;
    private String capital;
    private List<String> altSpellings;
    private String region;
    private String subregion;
    private Integer population;
    private List<Double> latlng;
    private String demonym;
    private Double area;
    private Double gini;
    private List<String> timezones;
    private List<String> borders;
    private String nativeName;
    private String numericCode;
    private List<Currency> currencies;
    private List<Language> languages;
    private Map<String, String> translations;
    private String flag;
    private List<RegionalBloc> regionalBlocs;
    private String cioc;
}
