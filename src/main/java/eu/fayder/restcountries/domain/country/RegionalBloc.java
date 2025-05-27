package eu.fayder.restcountries.domain.country;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RegionalBloc {
    private String acronym;
    private String name;
    private List<String> otherAcronyms;
    private List<String> otherNames;

    public boolean isAlternativeName(String name) {
        if (this.name.equalsIgnoreCase(name) || this.acronym.equalsIgnoreCase(name))
            return true;
        if (otherNames != null) {
            for (String otherName : otherNames) {
                if (otherName.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        if (otherAcronyms != null) {
            for (String otherAcronym : otherAcronyms) {
                if (otherAcronym.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }

}
