package eu.fayder.restcountries.api.mapper.country;

import eu.fayder.restcountries.domain.CountryResponse;
import eu.fayder.restcountries.domain.country.Coordinates;
import eu.fayder.restcountries.domain.country.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Mapper(componentModel = "spring", uses = {LanguageMapper.class})
public interface CountryMapper {

    @Mapping(source = "codes.isoAlpha2Code", target = "alpha2Code")
    @Mapping(source = "codes.isoAlpha3Code", target = "alpha3Code")
    @Mapping(source = "codes.callingCodes", target = "callingCodes")
    @Mapping(source = "codes.isoNumericCode", target = "numericCode")
    @Mapping(source = "codes.topLevelDomain", target = "topLevelDomain")
    @Mapping(source = "codes.iocCode", target = "cioc")

    @Mapping(source = "geography.capital", target = "capital")
    @Mapping(source = "geography.alternativeSpellings", target = "altSpellings")
    @Mapping(source = "geography.region", target = "region")
    @Mapping(source = "geography.subregion", target = "subregion")
    @Mapping(target = "latlng", source = "geography.coordinates", qualifiedByName = "coordinatesToLatLng")
    @Mapping(source = "geography.timezones", target = "timezones")
    @Mapping(source = "geography.borders", target = "borders")

    @Mapping(source = "demographics.population", target = "population")
    @Mapping(source = "demographics.demonym", target = "demonym")
    @Mapping(source = "demographics.area", target = "area")
    @Mapping(source = "demographics.giniCoefficient", target = "gini")
    @Mapping(source = "demographics.nativeName", target = "nativeName")
    CountryResponse toResponse(Country country);

    default URI map(String value) {
        return value != null ? URI.create(value) : null;
    }

    @Named("coordinatesToLatLng")
    static List<BigDecimal> coordinatesToLatLng(Coordinates coordinates) {
        if (coordinates == null) return null;
        return List.of(BigDecimal.valueOf(coordinates.getLatitude()),
                BigDecimal.valueOf(coordinates.getLongitude()));
    }

}
