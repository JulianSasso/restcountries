/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package eu.fayder.restcountries.domain.countryinfo.country;

import lombok.Getter;

import java.util.List;

@Getter
public class Country {

    private String name;

    private List<String> topLevelDomain;

    private String alpha2Code;

    private String alpha3Code;

    private List<String> callingCodes;

    private String capital;

    private List<String> altSpellings;

    private String region;

    private String subregion;

    private Integer population;

    // Coordinates: Latitude and Longitude
    private List<Double> latlng;

    private String demonym;

    private Double area;

    private Double gini;

    private List<String> timezones;

    private List<String> borders;

    private String nativeName;

    private String numericCode;

    private List<Currency> currencies;

    private List<Language> languages;

    private Translations translations;

    private String flag;

    private List<RegionalBloc> regionalBlocs;

    private String cioc;

}
