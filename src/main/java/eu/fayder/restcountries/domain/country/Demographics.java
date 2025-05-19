package eu.fayder.restcountries.domain.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Demographics {
    private Integer population;
    private String demonym;
    private Double area;
    private Double gini;
    private String nativeName;
}
