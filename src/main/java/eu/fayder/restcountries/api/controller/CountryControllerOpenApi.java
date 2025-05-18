package eu.fayder.restcountries.api.controller;

import eu.fayder.restcountries.api.CountryApi;
import eu.fayder.restcountries.domain.Country;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CountryControllerOpenApi implements CountryApi {

    @Override
    public ResponseEntity<List<Country>> getAllCountries(String fields) {
        return CountryApi.super.getAllCountries(fields);
    }

    @Override
    public ResponseEntity<List<Country>> getCountriesByAlphaCodes(String codes, String fields) {
        return CountryApi.super.getCountriesByAlphaCodes(codes, fields);
    }

    @Override
    public ResponseEntity<List<Country>> getCountriesByCallingCode(String callingcode, String fields) {
        return CountryApi.super.getCountriesByCallingCode(callingcode, fields);
    }

    @Override
    public ResponseEntity<List<Country>> getCountriesByCapital(String capital, String fields) {
        return CountryApi.super.getCountriesByCapital(capital, fields);
    }

    @Override
    public ResponseEntity<List<Country>> getCountriesByCurrency(String currency, String fields) {
        return CountryApi.super.getCountriesByCurrency(currency, fields);
    }

    @Override
    public ResponseEntity<List<Country>> getCountriesByLanguage(String language, String fields) {
        return CountryApi.super.getCountriesByLanguage(language, fields);
    }

    @Override
    public ResponseEntity<List<Country>> getCountriesByName(String name, String fields) {
        return CountryApi.super.getCountriesByName(name, fields);
    }

    @Override
    public ResponseEntity<List<Country>> getCountriesByRegion(String region, String fields) {
        return CountryApi.super.getCountriesByRegion(region, fields);
    }

    @Override
    public ResponseEntity<List<Country>> getCountriesByRegionalBloc(String regionalbloc, String fields) {
        return CountryApi.super.getCountriesByRegionalBloc(regionalbloc, fields);
    }

    @Override
    public ResponseEntity<Country> getCountryByAlphaCode(String code, String fields) {
        return CountryApi.super.getCountryByAlphaCode(code, fields);
    }
}
