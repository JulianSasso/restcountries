package eu.fayder.restcountries.infrastructure.persistence.json;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setGini(Double gini) {
        this.gini = gini;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public void setTranslations(Map<String, String> translations) {
        this.translations = translations;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }
}
