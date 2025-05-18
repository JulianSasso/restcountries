package eu.fayder.restcountries.api.mapper.country;

import eu.fayder.restcountries.domain.CountryResponse;
import eu.fayder.restcountries.domain.CurrencyResponse;
import eu.fayder.restcountries.domain.LanguageResponse;
import eu.fayder.restcountries.domain.countryinfo.country.Country;

import java.math.BigDecimal;

public class CountryMapperImpl implements CountryMapper {

    @Override
    public CountryResponse toResponse(Country country) {
        if (country == null) {
            return null;
        }

        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setName(country.getName());
        countryResponse.setAlpha2Code(country.getAlpha2Code());
        countryResponse.setAlpha3Code(country.getAlpha3Code());
        countryResponse.setNumericCode(country.getNumericCode());
        countryResponse.setDemonym(country.getDemonym());
        countryResponse.setRegion(country.getRegion());
        countryResponse.setSubregion(country.getSubregion());
        countryResponse.setPopulation(country.getPopulation());
        countryResponse.setArea(BigDecimal.valueOf(country.getArea()));
        countryResponse.setTimezones(country.getTimezones());
        countryResponse.setCurrencies(
                country.getCurrencies().stream()
                        .map(currency -> new CurrencyResponse(currency.getCode(), currency.getName(), currency.getSymbol()))
                .toList());
        countryResponse.setLanguages(
                country.getLanguages().stream()
                        .map(language -> new LanguageResponse(language.getIso639_1(), language.getIso639_2(), language.getName(), language.getNativeName()))
                .toList());

        return countryResponse;
    }
}
