package eu.fayder.restcountries.domain.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Geography {
    private String capital;
    private List<String> alternativeSpellings;
    private String region;
    private String subregion;
    private Coordinates coordinates;
    private List<String> timezones;
    private List<String> borders;
}
