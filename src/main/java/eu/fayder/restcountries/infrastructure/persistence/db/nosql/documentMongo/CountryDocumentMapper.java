package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo;

import eu.fayder.restcountries.domain.country.*;
import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country.CountryDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryDocumentMapper {

    default Country toDomain(CountryDocument country){
        final Coordinates coordinates = country.getLatlng() != null && country.getLatlng().size() == 2
                ? new Coordinates(country.getLatlng().get(0), country.getLatlng().get(1))
                : null;
        return new Country(
                country.getName(),
            new CountryCodes(country.getTopLevelDomain(), country.getAlpha2Code(), country.getAlpha3Code(), country.getNumericCode(), country.getCioc(), country.getCallingCodes()),
            new Geography(country.getCapital(), country.getAltSpellings(), country.getRegion(), country.getSubregion(), coordinates, country.getTimezones(), country.getBorders()),
            new Demographics(country.getPopulation(), country.getDemonym(), country.getArea(), country.getGini(), country.getNativeName()),
                country.getCurrencies(),
                country.getLanguages(),
                country.getTranslations(),
                country.getFlag(),
                country.getRegionalBlocs()
        );
    }
}
