package eu.fayder.restcountries.domain.country;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = false)
public class Geography {
    private String capital;
    private List<String> altSpellings;
    private String region;
    private String subregion;
    private List<Double> latlng; // [lat, lng]
    private List<String> timezones;
    private List<String> borders;
}
