package mapper.country;

import eu.fayder.restcountries.domain.CountryResponse;
import eu.fayder.restcountries.domain.country.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.net.URI;

@Mapper(componentModel = "spring", uses = {LanguageMapper.class})
public interface CountryMapper {

    @Mapping(source = "codes.alpha2Code", target = "alpha2Code")
    @Mapping(source = "codes.alpha3Code", target = "alpha3Code")
    @Mapping(source = "codes.callingCodes", target = "callingCodes")
    @Mapping(source = "codes.numericCode", target = "numericCode")
    @Mapping(source = "codes.topLevelDomain", target = "topLevelDomain")
    @Mapping(source = "codes.cioc", target = "cioc")

    @Mapping(source = "geography.capital", target = "capital")
    @Mapping(source = "geography.altSpellings", target = "altSpellings")
    @Mapping(source = "geography.region", target = "region")
    @Mapping(source = "geography.subregion", target = "subregion")
    @Mapping(source = "geography.latlng", target = "latlng")
    @Mapping(source = "geography.timezones", target = "timezones")
    @Mapping(source = "geography.borders", target = "borders")

    @Mapping(source = "demographics.population", target = "population")
    @Mapping(source = "demographics.demonym", target = "demonym")
    @Mapping(source = "demographics.area", target = "area")
    @Mapping(source = "demographics.gini", target = "gini")
    @Mapping(source = "demographics.nativeName", target = "nativeName")
    CountryResponse toResponse(Country country);

    default URI map(String value) {
        return value != null ? URI.create(value) : null;
    }

}
