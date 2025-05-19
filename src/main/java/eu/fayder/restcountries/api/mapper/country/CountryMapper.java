package eu.fayder.restcountries.api.mapper.country;

import eu.fayder.restcountries.domain.CountryResponse;
import eu.fayder.restcountries.domain.country.Country;
import org.mapstruct.Mapper;

import java.net.URI;

@Mapper(componentModel = "spring", uses = {LanguageMapper.class})
public interface CountryMapper {

    CountryResponse toResponse(Country country);

    default URI map(String value) {
        return value != null ? URI.create(value) : null;
    }

}
