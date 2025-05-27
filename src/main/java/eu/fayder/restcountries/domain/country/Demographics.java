package eu.fayder.restcountries.domain.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Builder
public class Demographics {
    private Integer population;
    private String demonym;
    private Double area;
    private Double giniCoefficient;
    private String nativeName;
}
