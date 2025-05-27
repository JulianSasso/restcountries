package eu.fayder.restcountries.infrastructure.persistence.file.json.country;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionalBlocJson {
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
