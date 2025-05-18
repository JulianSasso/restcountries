package eu.fayder.restcountries.application.usecase;

import eu.fayder.restcountries.domain.countryinfo.CountryInformationService;
import eu.fayder.restcountries.domain.countryinfo.CountryRepository;
import eu.fayder.restcountries.domain.countryinfo.country.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CountryInformationServiceImpl implements CountryInformationService {

    private static final Logger LOG = LoggerFactory.getLogger(CountryInformationServiceImpl.class);
    private final CountryRepository countryRepository;

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getByAlpha(String alpha) {
        if(alpha.length() == 2)
            return countryRepository.findByAlpha2Code(alpha);
        if(alpha.length() == 3)
            return countryRepository.findByAlpha3Code(alpha);
        return Optional.empty();
    }

    @Override
    // TODO: Optimizar para no tener que hacer una busqueda por cada código
    public List<Country> getByAlphaCodeList(Set<String> codes) {
        List<Country> result = new ArrayList<>();
        codes.forEach(code -> getByAlpha(code).ifPresent(result::add));

        return result;
    }

    @Override
    public List<Country> getByNameContaining(String name) {
        return countryRepository.findByNameContaining(name);
    }

    @Override
    public List<Country> getByCallingCode(String callingCode) {
        return countryRepository.findByCallingCode(callingCode);
    }

    @Override
    public List<Country> getByCapital(String capital) {
        return countryRepository.findByCapitalContaining(capital);
    }

    @Override
    public List<Country> getByRegion(String region) {
        return countryRepository.findByRegion(region);
    }

    @Override
    public List<Country> getBySubregion(String subregion) {
        return countryRepository.findBySubregion(subregion);
    }

    @Override
    public List<Country> getByCurrency(String currency) {
        return countryRepository.findByCurrency(currency);
    }

    @Override
    public List<Country> getByLanguage(String language) {
        if(language.length() == 2)
            return countryRepository.findByLanguageTwoLetterIsoCode(language);
        if(language.length() == 3)
            return countryRepository.findByLanguageThreeLetterIsoCode(language);
        return new ArrayList<>();
    }

    @Override
    public List<Country> getByDemonym(String demonym) {
        return countryRepository.findByDemonym(demonym);
    }

    @Override
    public List<Country> getByRegionalBloc(String regionalBloc) {
        return countryRepository.findByRegionalBloc(regionalBloc);
    }

}
