package eu.fayder.restcountries.domain.country;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Geography {
    private String capital;
    private List<String> altSpellings;
    private String region;
    private String subregion;
    private Coordinates coordinates; // [lat, lng]
    private List<String> timezones;
    private List<String> borders;
}
