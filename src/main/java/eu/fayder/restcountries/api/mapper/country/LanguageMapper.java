package eu.fayder.restcountries.api.mapper.country;

import eu.fayder.restcountries.domain.LanguageResponse;
import eu.fayder.restcountries.domain.country.Language;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    @Mapping(source = "iso639_1", target = "iso6391")
    @Mapping(source = "iso639_2", target = "iso6392")
    LanguageResponse toResponse(Language language);
}
