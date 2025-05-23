package eu.fayder.restcountries.infrastructure.persistence.json;

import eu.fayder.restcountries.domain.country.Country;
import eu.fayder.restcountries.domain.country.CountryCodes;
import eu.fayder.restcountries.domain.country.Demographics;
import eu.fayder.restcountries.domain.country.Geography;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryJsonMapper {
    default Country toDomain(CountryJson json){
        return new Country(
                json.getName(),
            new CountryCodes(json.getTopLevelDomain(), json.getAlpha2Code(), json.getAlpha3Code(), json.getNumericCode(), json.getCioc(), json.getCallingCodes()),
            new Geography(json.getCapital(), json.getAltSpellings(), json.getRegion(), json.getSubregion(), json.getLatlng(), json.getTimezones(), json.getBorders()),
            new Demographics(json.getPopulation(), json.getDemonym(), json.getArea(), json.getGini(), json.getNativeName()),
                json.getCurrencies(),
                json.getLanguages(),
                json.getTranslations(),
                json.getFlag(),
                json.getRegionalBlocs()
        );
    }

}
