package eu.fayder.restcountries.api.controller;

import eu.fayder.restcountries.api.CountryApi;
import eu.fayder.restcountries.api.mapper.country.CountryMapper;
import eu.fayder.restcountries.domain.CountryResponse;
import eu.fayder.restcountries.domain.countryinfo.CountryInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.List;

@RequestMapping("rest/v3")
@RestController
@RequiredArgsConstructor
public class CountryControllerOpenApi implements CountryApi {

    private final CountryInformationService countryService;
    private final CountryMapper countryMapper;

    @Override
    @GetMapping("all")
    public ResponseEntity<List<CountryResponse>> getAllCountries(String fields) {
        return ResponseEntity.ok(countryService.getAll().stream().map(countryMapper::toResponse).toList());
    }

    @Override
    public ResponseEntity<List<CountryResponse>> getCountriesByAlphaCodes(String codes, String fields) {
        return CountryApi.super.getCountriesByAlphaCodes(codes, fields);
    }

    @Override
    public ResponseEntity<List<CountryResponse>> getCountriesByCallingCode(String callingcode, String fields) {
        return CountryApi.super.getCountriesByCallingCode(callingcode, fields);
    }

    @Override
    public ResponseEntity<List<CountryResponse>> getCountriesByCapital(String capital, String fields) {
        return CountryApi.super.getCountriesByCapital(capital, fields);
    }

    @Override
    public ResponseEntity<List<CountryResponse>> getCountriesByCurrency(String currency, String fields) {
        return CountryApi.super.getCountriesByCurrency(currency, fields);
    }

    @Override
    public ResponseEntity<List<CountryResponse>> getCountriesByLanguage(String language, String fields) {
        return CountryApi.super.getCountriesByLanguage(language, fields);
    }

    @Override
    public ResponseEntity<List<CountryResponse>> getCountriesByName(String name, String fields) {
        return CountryApi.super.getCountriesByName(name, fields);
    }

    @Override
    public ResponseEntity<List<CountryResponse>> getCountriesByRegion(String region, String fields) {
        return CountryApi.super.getCountriesByRegion(region, fields);
    }

    @Override
    public ResponseEntity<List<CountryResponse>> getCountriesByRegionalBloc(String regionalbloc, String fields) {
        return CountryApi.super.getCountriesByRegionalBloc(regionalbloc, fields);
    }

    @Override
    public ResponseEntity<CountryResponse> getCountryByAlphaCode(String code, String fields) {
        return CountryApi.super.getCountryByAlphaCode(code, fields);
    }
}
