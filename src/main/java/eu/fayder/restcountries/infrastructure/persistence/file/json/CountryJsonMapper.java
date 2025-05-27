package eu.fayder.restcountries.infrastructure.persistence.file.json;

import eu.fayder.restcountries.domain.country.*;
import eu.fayder.restcountries.infrastructure.persistence.file.json.country.CountryJson;
import eu.fayder.restcountries.infrastructure.persistence.file.json.country.CurrencyJson;
import eu.fayder.restcountries.infrastructure.persistence.file.json.country.LanguageJson;
import eu.fayder.restcountries.infrastructure.persistence.file.json.country.RegionalBlocJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryJsonMapper {
    default Country toDomain(CountryJson json){
        return Country.builder()
                .name(json.getName())
                .codes(countryCodesFromCountryJson(json))
                .geography(geographyFromCountryJson(json))
                .demographics(demographicsFromCountryJson(json))
                .currencies(json.getCurrencies().stream().map(this::toDomain).toList())
                .languages(json.getLanguages().stream().map(this::toDomain).toList())
                .translations(json.getTranslations())
                .flag(json.getFlag())
                .regionalBlocs(json.getRegionalBlocs().stream().map(this::toDomain).toList())
                .build();
    }

    @Mapping(source = "iso639_1", target = "isoTwoLetterCode")
    @Mapping(source = "iso639_2", target = "isoThreeLetterCode")
    Language toDomain(LanguageJson json);

    Currency toDomain(CurrencyJson json);

    RegionalBloc toDomain(RegionalBlocJson json);

    default Demographics demographicsFromCountryJson(CountryJson country) {
        return Demographics.builder()
                .population(country.getPopulation())
                .demonym(country.getDemonym())
                .area(country.getArea())
                .giniCoefficient(country.getGini())
                .nativeName(country.getNativeName())
                .build();
    }

    default CountryCodes countryCodesFromCountryJson(CountryJson country) {
        return CountryCodes.builder()
                .topLevelDomain(country.getTopLevelDomain())
                .isoAlpha2Code(country.getAlpha2Code())
                .isoAlpha3Code(country.getAlpha3Code())
                .isoNumericCode(country.getNumericCode())
                .iocCode(country.getCioc())
                .callingCodes(country.getCallingCodes())
                .build();
    }

    default Geography geographyFromCountryJson(CountryJson country){
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
