package eu.fayder.restcountries.domain.country;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = false)
public class Demographics {
    private Integer population;
    private String demonym;
    private Double area;
    private Double gini;
    private String nativeName;
}
