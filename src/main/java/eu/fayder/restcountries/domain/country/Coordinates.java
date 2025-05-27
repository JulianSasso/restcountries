package eu.fayder.restcountries.domain.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    private Double latitude;
    private Double longitude;
}
