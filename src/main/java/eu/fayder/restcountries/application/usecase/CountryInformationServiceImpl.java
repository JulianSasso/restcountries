package eu.fayder.restcountries.application.usecase;

import eu.fayder.restcountries.domain.countryinfo.CountryInformationService;
import eu.fayder.restcountries.domain.countryinfo.country.*;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryInformationServiceImpl implements CountryInformationService {

    private static final Logger LOG = LoggerFactory.getLogger(CountryInformationServiceImpl.class);
    private static final String SEPARATOR = ";";

    // TODO: Mover a repository
    private static final List<Country> countries = loadJson("countriesV2.json");

    @Override
    public List<Country> getAll() {
        return countries;
    }

    @Override
    public Country getByAlpha(String alpha) {
        int alphaLength = alpha.length();
        for (Country country : countries) {
            if (alphaLength == 2) {
                if (country.getAlpha2Code().equalsIgnoreCase(alpha)) {
                    return country;
                }
            } else if (alphaLength == 3) {
                if (country.getAlpha3Code().equalsIgnoreCase(alpha)) {
                    return country;
                }
            }
        }
        return null;
    }

    @Override
    public List<Country> getByCodeList(String codeList) {
        List<Country> result = new ArrayList<>();
        if(codeList == null) return result;

        String[] codes = codeList.split(SEPARATOR);
        for(String code : codes) {
            Country country = getByAlpha(code);
            if(!result.contains(country))
                result.add(country);
        }
        return result;
    }

    @Override
    public List<Country> getByName(String name, boolean fullText) {
        if(fullText) {
            return fulltextSearch(name);
        } else {
            return substringSearch(name);
        }
    }

    @Override
    public List<Country> getByCallingCode(String callingCode) {
        List<Country> result = new ArrayList<>();
        for(Country country : countries) {
            for(String c : country.getCallingCodes()) {
                if(c.equals(callingCode))
                    result.add(country);
            }
        }
        return result;
    }

    @Override
    public List<Country> getByCapital(String capital) {
        List<Country> result = new ArrayList<>();
        for(Country country : countries) {
            if(normalize(country.getCapital().toLowerCase()).contains(normalize(capital.toLowerCase()))) {
                result.add(country);
            }
        }
        return result;
    }

    @Override
    public List<Country> getByRegion(String region) {
        List<Country> result = new ArrayList<>();
        for(Country country : countries) {
            if(country.getRegion().equalsIgnoreCase(region)) {
                result.add(country);
            }
        }
        return result;
    }

    @Override
    public List<Country> getBySubregion(String subregion) {
        List<Country> result = new ArrayList<>();
        for(Country country : countries) {
            if(country.getSubregion().equalsIgnoreCase(subregion)) {
                result.add(country);
            }
        }
        return result;
    }

    @Override
    public List<Country> getByCurrency(String currency) {
        List<Country> result = new ArrayList<>();
        for (Country country : countries) {
            for (Currency curr : country.getCurrencies()) {
                if (curr.getCode() != null && currency.equalsIgnoreCase(curr.getCode())) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    @Override
    public List<Country> getByLanguage(String language) {
        List<Country> result = new ArrayList<>();
        if (language.length() == 2) {
            for (Country country : countries) {
                for (Language lang : country.getLanguages()) {
                    if (language.toLowerCase().equals(lang.getIso639_1())) {
                        result.add(country);
                    }
                }
            }
        } else if (language.length() == 3) {
            for (Country country : countries) {
                for (Language lang : country.getLanguages()) {
                    if (language.toLowerCase().equals(lang.getIso639_2())) {
                        result.add(country);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<Country> getByDemonym(String demonym) {
        List<Country> result = new ArrayList<>();
        for (Country country : countries) {
            if (country.getDemonym().toLowerCase().equals(normalize(demonym.toLowerCase()))) {
                result.add(country);
            }
        }
        return result;
    }

    @Override
    public List<Country> getByRegionalBloc(String regionalBloc) {
        List<Country> result = new ArrayList<>();
        for (Country country : countries) {
            if(country.getRegionalBlocs() == null)
                continue;

            for (RegionalBloc countryRegionalBloc : country.getRegionalBlocs()) {
                if (countryRegionalBloc.getAcronym().equalsIgnoreCase(regionalBloc)
                        || countryRegionalBloc.getOtherAcronyms().contains(regionalBloc.toUpperCase())) {
                    result.add(country);
                }
            }
        }
        return result;
    }


    private List<Country> fulltextSearch(String name) {
        // Using 2 different 'for' loops to give priority to 'name' matches over alternative spellings
        List<Country> result = new ArrayList<>();
        for (Country country : countries) {
            if (normalize(country.getName().toLowerCase()).equals(normalize(name.toLowerCase()))) {
                result.add(country);
            }
        }
        for (Country country : countries) {
            for (String alternative : country.getAltSpellings()) {
                if (normalize(alternative.toLowerCase()).equals(normalize(name.toLowerCase()))
                        && !result.contains(country)) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    private List<Country> substringSearch(String name) {
        // Using 2 different 'for' loops to give priority to 'name' matches over alternative spellings
        List<Country> result = new ArrayList<>();
        for(Country country : countries) {
            if(normalize(country.getName().toLowerCase()).contains(normalize(name.toLowerCase()))) {
                result.add(country);
            }
        }
        for(Country country : countries) {
            for (String alternative : country.getAltSpellings()) {
                if( normalize(alternative.toLowerCase()).contains(normalize(name.toLowerCase()))
                        && !result.contains(country) ) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    protected String normalize(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    protected static List<Country> loadJson(String filename) {
        LOG.debug("Loading JSON " + filename);
        List<Country> countries = new ArrayList<>();
        InputStream is = CountryInformationServiceImpl.class.getClassLoader().getResourceAsStream(filename);
        Gson gson = new Gson();
        JsonReader reader;
        try {
            reader = new JsonReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            reader.beginArray();
            while(reader.hasNext()) {
                Country country = gson.fromJson(reader, Country.class);
                countries.add(country);
            }
        } catch (Exception e) {
            LOG.error("Could not load JSON " + filename);
        }
        return countries;
    }
}
