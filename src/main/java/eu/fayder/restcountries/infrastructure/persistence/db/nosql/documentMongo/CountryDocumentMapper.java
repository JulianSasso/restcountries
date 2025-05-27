package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo;

import eu.fayder.restcountries.domain.country.*;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country.CountryDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryDocumentMapper {

    default Country toDomain(CountryDocument country) {
        return new Country(
                country.getName(),
                countryCodesFromCountryDocument(country),
                geographyFromCountryDocument(country),
                demographicsFromCountryDocument(country),
                country.getCurrencies(),
                country.getLanguages(),
                country.getTranslations(),
                country.getFlag(),
                country.getRegionalBlocs()
        );
    }

    default Demographics demographicsFromCountryDocument(CountryDocument country) {
        return Demographics.builder()
                .population(country.getPopulation())
                .demonym(country.getDemonym())
                .area(country.getArea())
                .giniCoefficient(country.getGini())
                .nativeName(country.getNativeName())
                .build();
    }

    default CountryCodes countryCodesFromCountryDocument(CountryDocument country) {
        return CountryCodes.builder()
                .topLevelDomain(country.getTopLevelDomain())
                .isoAlpha2Code(country.getAlpha2Code())
                .isoAlpha3Code(country.getAlpha3Code())
                .isoNumericCode(country.getNumericCode())
                .iocCode(country.getCioc())
                .callingCodes(country.getCallingCodes())
                .build();
    }

    default Geography geographyFromCountryDocument(CountryDocument country){
        return Geography.builder()
                .capital(country.getCapital())
                .alternativeSpellings(country.getAltSpellings())
                .region(country.getRegion())
                .subregion(country.getSubregion())
                .coordinates(coordinatesFromLatlng(country.getLatlng()))
                .timezones(country.getTimezones())
                .borders(country.getBorders())
                .build();
    }

    default Coordinates coordinatesFromLatlng(List<Double> latlng){
        if(latlng == null || latlng.size() != 2) {
            return null;
        }

        return  Coordinates.builder()
                    .latitude(latlng.get(0))
                    .longitude(latlng.get(1))
                    .build();
    }
}
