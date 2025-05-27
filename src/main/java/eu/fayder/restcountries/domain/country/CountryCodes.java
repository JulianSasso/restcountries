package eu.fayder.restcountries.domain.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountryCodes {

    private List<String> topLevelDomain;
    private String isoAlpha2Code;
    private String isoAlpha3Code;
    private String isoNumericCode;
    private String iocCode;
    private List<String> callingCodes;
}
