package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo;

import eu.fayder.restcountries.domain.country.*;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country.CountryDocument;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country.CurrencyDocument;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country.LanguageDocument;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country.RegionalBlocDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryDocumentMapper {

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
    Country toDomain(CountryDocument document);

    @Named("toCoordinates")
    default Coordinates toCoordinates(List<Double> latlng) {
        if (latlng == null || latlng.size() != 2) return null;
        return Coordinates.builder()
                .latitude(latlng.get(0))
                .longitude(latlng.get(1))
                .build();
    }

    List<Language> toLanguages(List<LanguageDocument> document);
    List<RegionalBloc> toRegionalBlocs(List<RegionalBlocDocument> document);

    @Mapping(source = "iso639_1", target = "isoTwoLetterCode")
    @Mapping(source = "iso639_2", target = "isoThreeLetterCode")
    Language toDomain(LanguageDocument document);

    Currency toDomain(CurrencyDocument document);

    RegionalBloc toDomain(RegionalBlocDocument document);
}
