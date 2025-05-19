package eu.fayder.restcountries.infrastructure.persistence.json;

import eu.fayder.restcountries.domain.country.Country;
import eu.fayder.restcountries.domain.country.CountryCodes;
import eu.fayder.restcountries.domain.country.Demographics;
import eu.fayder.restcountries.domain.country.Geography;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryJsonMapper {

    public Country toDomain(CountryJson json) {
        return new Country(
            json.name,
            new CountryCodes(json.topLevelDomain, json.alpha2Code, json.alpha3Code, json.numericCode, json.cioc, json.callingCodes),
            new Geography(json.capital, json.altSpellings, json.region, json.subregion, json.latlng, json.timezones, json.borders),
            new Demographics(json.population, json.demonym, json.area, json.gini, json.nativeName),
            json.currencies,
            json.languages,
            json.translations,
            json.flag,
            json.regionalBlocs
        );
    }

    public List<Country> toDomainList(List<CountryJson> list) {
        return list.stream().map(this::toDomain).toList();
    }
}
