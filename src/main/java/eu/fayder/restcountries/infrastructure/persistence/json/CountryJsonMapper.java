package eu.fayder.restcountries.infrastructure.persistence.json;

import eu.fayder.restcountries.domain.country.Country;

import java.util.List;

public interface CountryJsonMapper {
    Country toDomain(CountryJson json);

    List<Country> toDomainList(List<CountryJson> list);
}
