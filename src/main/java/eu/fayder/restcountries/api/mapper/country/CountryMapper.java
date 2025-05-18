package eu.fayder.restcountries.api.mapper.country;

import eu.fayder.restcountries.domain.CountryResponse;
import eu.fayder.restcountries.domain.countryinfo.country.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.net.URI;

@Mapper(componentModel = "spring", uses = {LanguageMapper.class})
public interface CountryMapper {

    //@Mapping(target = "iso639_1", source = "iso6391")
    //@Mapping(target = "iso639_2", source = "iso6392")
    CountryResponse toResponse(Country country);

    default URI map(String value) {
        return value != null ? URI.create(value) : null;
    }

}
