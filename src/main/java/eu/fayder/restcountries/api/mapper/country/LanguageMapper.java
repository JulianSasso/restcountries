package eu.fayder.restcountries.api.mapper.country;

import eu.fayder.restcountries.domain.LanguageResponse;
import eu.fayder.restcountries.domain.country.Language;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    @Mapping(source = "isoTwoLetterCode", target = "iso6391")
    @Mapping(source = "isoThreeLetterCode", target = "iso6392")
    LanguageResponse toResponse(Language language);
}
