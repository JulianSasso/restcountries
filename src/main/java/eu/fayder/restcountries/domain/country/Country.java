/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package eu.fayder.restcountries.domain.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    private String name;
    private CountryCodes codes;
    private Geography geography;
    private Demographics demographics;
    private List<Currency> currencies;
    private List<Language> languages;
    private Map<String, String> translations;
    private String flag;
    private List<RegionalBloc> regionalBlocs;

}
