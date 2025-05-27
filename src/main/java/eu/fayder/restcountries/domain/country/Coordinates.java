package eu.fayder.restcountries.domain.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class Coordinates {
    private Double latitude;
    private Double longitude;
}
