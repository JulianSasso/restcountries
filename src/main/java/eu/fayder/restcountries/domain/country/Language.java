/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package eu.fayder.restcountries.domain.country;


import lombok.*;


@Getter
//@AllArgsConstructor
@Builder
public class Language {
    private String isoTwoLetterCode;
    private String isoThreeLetterCode;
    private String name;
    private String nativeName;
}
