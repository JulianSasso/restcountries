package eu.fayder.restcountries.domain.country;

import java.util.List;

/**
 * Created by fayder on 30/04/2017.
 *
 * @param otherAcronyms = new ArrayList<>();
 * @param otherNames    = new ArrayList<>();
 */

public record RegionalBloc(String acronym, String name, List<String> otherAcronyms, List<String> otherNames) {

    public boolean isAlternativeName(String name) {
        if (this.name.equalsIgnoreCase(name) || this.acronym.equalsIgnoreCase(name))
            return true;
        for (String otherName : otherNames()) {
            if (otherName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        for (String otherAcronym : otherAcronyms()) {
            if (otherAcronym.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
