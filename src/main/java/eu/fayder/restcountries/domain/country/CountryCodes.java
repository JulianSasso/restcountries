package eu.fayder.restcountries.domain.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountryCodes {

    private List<String> topLevelDomain;
    private String alpha2Code;
    private String alpha3Code;
    private String numericCode;
    private String cioc;
    private List<String> callingCodes;
}
