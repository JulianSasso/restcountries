package eu.fayder.restcountries.infrastructure.persistence.file.json;

import eu.fayder.restcountries.domain.country.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryJsonMapper {
    default Country toDomain(CountryJson json){
        final Coordinates coordinates = json.getLatlng() != null && json.getLatlng().size() == 2
                ? new Coordinates(json.getLatlng().get(0), json.getLatlng().get(1))
                : null;
        return new Country(
                json.getName(),
            new CountryCodes(json.getTopLevelDomain(), json.getAlpha2Code(), json.getAlpha3Code(), json.getNumericCode(), json.getCioc(), json.getCallingCodes()),
            new Geography(json.getCapital(), json.getAltSpellings(), json.getRegion(), json.getSubregion(), coordinates, json.getTimezones(), json.getBorders()),
            new Demographics(json.getPopulation(), json.getDemonym(), json.getArea(), json.getGini(), json.getNativeName()),
                json.getCurrencies(),
                json.getLanguages(),
                json.getTranslations(),
                json.getFlag(),
                json.getRegionalBlocs()
        );
    }

}
