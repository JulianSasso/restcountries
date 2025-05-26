package persistence.db.nosql.documentMongo;

import country.Country;
import country.CountryCodes;
import country.Demographics;
import country.Geography;
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
