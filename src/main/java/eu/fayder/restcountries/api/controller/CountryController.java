package eu.fayder.restcountries.api.controller;

import eu.fayder.restcountries.api.CountryApi;
import eu.fayder.restcountries.api.mapper.country.CountryMapper;
import eu.fayder.restcountries.domain.CountryResponse;
import eu.fayder.restcountries.domain.CountryInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@RequestMapping("rest/v2")
@RestController
@RequiredArgsConstructor
public class CountryController implements CountryApi {

    private final CountryInformationService countryService;
    private final CountryMapper countryMapper;

    @Override
    @GetMapping("all")
    public ResponseEntity<List<CountryResponse>> getAllCountries(String fields, @Min(1) Integer page, @Min(1) @Max(50) Integer pageSize) {
        return ResponseEntity.ok(countryService.getAll(page, pageSize)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }

    @Override
    @GetMapping("alpha")
    public ResponseEntity<List<CountryResponse>> getCountriesByAlphaCodes(String codes, String fields) {
        if(codes == null || codes.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Set<String> codeSet = Set.of(codes.split(";"));
        return ResponseEntity.ok(countryService.getByAlphaCodeList(codeSet)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }

    @Override
    @GetMapping("alpha/{code}")
    public ResponseEntity<CountryResponse> getCountryByAlphaCode(@PathVariable String code, String fields) {
        if(code == null || code.length() < 2 || code.length() > 3) {
            return ResponseEntity.badRequest().build();
        }

        return countryService.getByAlpha(code)
                .map(countryMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Override
    @GetMapping("callingcode/{callingCode}")
    public ResponseEntity<List<CountryResponse>> getCountriesByCallingCode(@PathVariable String callingCode, String fields) {
        return ResponseEntity.ok(countryService.getByCallingCode(callingCode)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }

    @Override
    @GetMapping("capital/{capital}")
    public ResponseEntity<List<CountryResponse>> getCountriesByCapital(@PathVariable String capital, String fields) {
        return ResponseEntity.ok(countryService.getByCapital(capital)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }

    @Override
    @GetMapping("currency/{currency}")
    public ResponseEntity<List<CountryResponse>> getCountriesByCurrency(@PathVariable String currency, String fields) {
        return ResponseEntity.ok(countryService.getByCurrency(currency)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }

    @Override
    @GetMapping("lang/{language}")
    public ResponseEntity<List<CountryResponse>> getCountriesByLanguage(@PathVariable String language, String fields) {
        return ResponseEntity.ok(countryService.getByLanguage(language)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }

    @Override
    @GetMapping("name/{name}")
    public ResponseEntity<List<CountryResponse>> getCountriesByName(@PathVariable String name, String fields) {
        return ResponseEntity.ok(countryService.getByNameContaining(name)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }

    @Override
    @GetMapping("region/{region}")
    public ResponseEntity<List<CountryResponse>> getCountriesByRegion(@PathVariable String region, String fields) {
        return ResponseEntity.ok(countryService.getByRegion(region)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }

    @Override
    @GetMapping("regionalbloc/{regionalBloc}")
    public ResponseEntity<List<CountryResponse>> getCountriesByRegionalBloc(@PathVariable String regionalBloc, String fields) {
        return ResponseEntity.ok(countryService.getByRegionalBloc(regionalBloc)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }

    @Override
    @GetMapping("demonym/{demonym}")
    public ResponseEntity<List<CountryResponse>> getCountriesByDemonym(@PathVariable String demonym, String fields) {
        return ResponseEntity.ok(countryService.getByDemonym(demonym)
                .stream()
                .map(countryMapper::toResponse)
                .toList());
    }
}
