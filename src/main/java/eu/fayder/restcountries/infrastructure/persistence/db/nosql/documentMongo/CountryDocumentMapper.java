package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo;

import eu.fayder.restcountries.domain.country.Country;
import eu.fayder.restcountries.domain.country.CountryCodes;
import eu.fayder.restcountries.domain.country.Demographics;
import eu.fayder.restcountries.domain.country.Geography;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryDocumentMapper {

    default Country toDomain(CountryDocument country){
        return new Country(
                country.getName(),
            new CountryCodes(country.getTopLevelDomain(), country.getAlpha2Code(), country.getAlpha3Code(), country.getNumericCode(), country.getCioc(), country.getCallingCodes()),
            new Geography(country.getCapital(), country.getAltSpellings(), country.getRegion(), country.getSubregion(), country.getLatlng(), country.getTimezones(), country.getBorders()),
            new Demographics(country.getPopulation(), country.getDemonym(), country.getArea(), country.getGini(), country.getNativeName()),
                country.getCurrencies(),
                country.getLanguages(),
                country.getTranslations(),
                country.getFlag(),
                country.getRegionalBlocs()
        );
    }
}
