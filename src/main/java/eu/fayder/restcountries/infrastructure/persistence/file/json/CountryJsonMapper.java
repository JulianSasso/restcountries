package eu.fayder.restcountries.infrastructure.persistence.file.json;

import eu.fayder.restcountries.domain.country.*;
import eu.fayder.restcountries.infrastructure.persistence.file.json.country.CountryJson;
import eu.fayder.restcountries.infrastructure.persistence.file.json.country.CurrencyJson;
import eu.fayder.restcountries.infrastructure.persistence.file.json.country.LanguageJson;
import eu.fayder.restcountries.infrastructure.persistence.file.json.country.RegionalBlocJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryJsonMapper {

    @Mapping(source = "latlng", target = "geography.coordinates", qualifiedByName = "toCoordinates")
    @Mapping(source = "topLevelDomain", target = "codes.topLevelDomain")
    @Mapping(source = "alpha2Code", target = "codes.isoAlpha2Code")
    @Mapping(source = "alpha3Code", target = "codes.isoAlpha3Code")
    @Mapping(source = "numericCode", target = "codes.isoNumericCode")
    @Mapping(source = "cioc", target = "codes.iocCode")
    @Mapping(source = "callingCodes", target = "codes.callingCodes")
    @Mapping(source = "capital", target = "geography.capital")
    @Mapping(source = "altSpellings", target = "geography.alternativeSpellings")
    @Mapping(source = "region", target = "geography.region")
    @Mapping(source = "subregion", target = "geography.subregion")
    @Mapping(source = "timezones", target = "geography.timezones")
    @Mapping(source = "borders", target = "geography.borders")
    @Mapping(source = "population", target = "demographics.population")
    @Mapping(source = "demonym", target = "demographics.demonym")
    @Mapping(source = "area", target = "demographics.area")
    @Mapping(source = "gini", target = "demographics.giniCoefficient")
    @Mapping(source = "nativeName", target = "demographics.nativeName")
    Country toDomain(CountryJson json);

    @Named("toCoordinates")
    default Coordinates toCoordinates(List<Double> latlng) {
        if (latlng == null || latlng.size() != 2) return null;
        return Coordinates.builder()
                .latitude(latlng.get(0))
                .longitude(latlng.get(1))
                .build();
    }

    List<Language> toLanguages(List<LanguageJson> json);
    List<RegionalBloc> toRegionalBlocs(List<RegionalBlocJson> json);

    @Mapping(source = "iso639_1", target = "isoTwoLetterCode")
    @Mapping(source = "iso639_2", target = "isoThreeLetterCode")
    Language toDomain(LanguageJson json);

    Currency toDomain(CurrencyJson json);

    RegionalBloc toDomain(RegionalBlocJson json);

}
