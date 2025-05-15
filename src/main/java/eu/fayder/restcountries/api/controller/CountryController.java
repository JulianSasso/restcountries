/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package eu.fayder.restcountries.api.controller;

import com.google.gson.*;
import eu.fayder.restcountries.domain.countryinfo.CountryInformationService;
import eu.fayder.restcountries.domain.countryinfo.country.ResponseEntity;
import eu.fayder.restcountries.domain.countryinfo.country.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("rest/v2")
@RestController
public class CountryController {

    @Autowired
    CountryInformationService countryService;

    private static final Logger LOG = LoggerFactory.getLogger(CountryController.class);
    private static final String SEPARATOR = ";";

    @GetMapping("all")
    public Object getAllCountries(@RequestParam(required = false) String fields) {
        return this.getCountries(fields);
    }

    public Object getCountries(String fields) {
        LOG.info("Getting all");
        List<Country> countries = countryService.getAll();
        return parsedCountries(countries, fields);
    }

    @GetMapping("alpha/{alphacode}")
    public Object getByAlpha(@PathVariable("alphacode") String alpha, @RequestParam(required = false) String fields) {
        LOG.info("Getting by alpha " + alpha);
        if (isEmpty(alpha) || alpha.length() < 2 || alpha.length() > 3) {
            return getResponse(Response.Status.BAD_REQUEST);
        }
        Country country = countryService.getByAlpha(alpha);
        if (country != null) {
            return parsedCountry(country, fields);
        }
        return getResponse(Response.Status.NOT_FOUND);
    }

    @GetMapping("alpha")
    public Object getByAlphaList(@RequestParam("codes") String codes, @RequestParam(required = false) String fields) {
        LOG.info("Getting by list " + codes);
        if (isEmpty(codes) || codes.length() < 2 || (codes.length() > 3 && !codes.contains(";"))) {
            return getResponse(Response.Status.BAD_REQUEST);
        }
        try {
            List<Country> countries = countryService.getByCodeList(codes);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("currency/{currency}")
    public Object getByCurrency(@PathVariable("currency") String currency, @RequestParam(required = false) String fields) {
        LOG.info("Getting by currency " + currency);
        if (isEmpty(currency) || currency.length() != 3) {
            return getResponse(Response.Status.BAD_REQUEST);
        }
        try {
            List<Country> countries = countryService.getByCurrency(currency);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("name/{name}")
    public Object getByName(@PathVariable("name") String name, @RequestParam(required = false, defaultValue = "false") Boolean fullText, @RequestParam(required = false) String fields) {
        LOG.info("Getting by name " + name);
        try {
            List<Country> countries = countryService.getByName(name, fullText);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("callingcode/{callingcode}")
    public Object getByCallingCode(@PathVariable("callingcode") String callingcode, @RequestParam(required = false) String fields) {
        LOG.info("Getting by calling code " + callingcode);
        try {
            List<Country> countries = countryService.getByCallingCode(callingcode);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("capital/{capital}")
    public Object getByCapital(@PathVariable("capital") String capital, @RequestParam(required = false) String fields) {
        LOG.info("Getting by capital " + capital);
        try {
            List<Country> countries = countryService.getByCapital(capital);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("region/{region}")
    public Object getByRegion(@PathVariable("region") String region, @RequestParam(required = false) String fields) {
        LOG.info("Getting by region " + region);
        try {
            List<Country> countries = countryService.getByRegion(region);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("subregion/{subregion}")
    public Object getBySubRegion(@PathVariable("subregion") String subregion, @RequestParam(required = false) String fields) {
        LOG.info("Getting by sub region " + subregion);
        try {
            List<Country> countries = countryService.getBySubregion(subregion);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("lang/{lang}")
    public Object getByLanguage(@PathVariable("lang") String language, @RequestParam(required = false) String fields) {
        LOG.info("Getting by language " + language);
        try {
            List<Country> countries = countryService.getByLanguage(language);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("demonym/{demonym}")
    public Object getByDemonym(@PathVariable("demonym") String demonym, @RequestParam(required = false) String fields) {
        LOG.info("Getting by demonym " + demonym);
        try {
            List<Country> countries = countryService.getByDemonym(demonym);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("regionalbloc/{regionalbloc}")
    public Object getByRegionalBloc(@PathVariable("regionalbloc") String regionalBlock, @RequestParam(required = false) String fields) {
        LOG.info("Getting by regional bloc " + regionalBlock);
        try {
            List<Country> countries = countryService.getByRegionalBloc(regionalBlock);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    private Response getResponse(Response.Status status) {
        Gson gson = new Gson();
        return Response
                .status(status)
                .entity(gson.toJson(new ResponseEntity(status.getStatusCode(),
                        status.getReasonPhrase()))).build();
    }

    private Object parsedCountry(Country country, String fields) {
        if (fields == null || fields.isEmpty()) {
            return country;
        } else {
            return getCountryJson(country, Arrays.asList(fields.split(SEPARATOR)));
        }
    }

    private Object parsedCountries(List<Country> countries, String excludedFields) {
        if (excludedFields == null || excludedFields.isEmpty()) {
            return countries;
        } else {
            return getCountriesJson(countries, Arrays.asList(excludedFields.split(SEPARATOR)));
        }
    }

    private String getCountryJson(Country country, List<String> fields) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(gson.toJson(country)).getAsJsonObject();

        List<String> excludedFields = getExcludedFields(fields);
        for (String field : excludedFields) {
            jsonObject.remove(field);
        }
        return jsonObject.toString();
    }

    private String getCountriesJson(List<Country> countries, List<String> fields) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(gson.toJson(countries)).getAsJsonArray();
        JsonArray resultArray = new JsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);

            List<String> excludedFields = getExcludedFields(fields);
            for (String excludedField : excludedFields) {
                jsonObject.remove(excludedField);
            }
            resultArray.add(jsonObject);
        }
        return resultArray.toString();
    }

    private List<String> getExcludedFields(List<String> fields) {
        List<String> excludedFields = new ArrayList<>(Arrays.asList(COUNTRY_FIELDS));
        excludedFields.removeAll(fields);
        return excludedFields;
    }

    private static final String[] COUNTRY_FIELDS = new String[]{
            "name",
            "topLevelDomain",
            "alpha2Code",
            "alpha3Code",
            "callingCodes",
            "capital",
            "altSpellings",
            "region",
            "subregion",
            "translations",
            "population",
            "latlng",
            "demonym",
            "area",
            "gini",
            "timezones",
            "borders",
            "nativeName",
            "numericCode",
            "currencies",
            "languages",
            "flag",
            "regionalBlocs",
            "cioc"
    };

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
