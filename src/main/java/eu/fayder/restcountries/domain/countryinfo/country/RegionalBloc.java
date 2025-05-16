package eu.fayder.restcountries.domain.countryinfo.country;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fayder on 30/04/2017.
 */

public class RegionalBloc {

    @Getter
    private String acronym;
    @Getter
    private String name;
    private List<String> otherAcronyms;
    private List<String> otherNames;

    /*public RegionalBloc(String acronym, String name) {
        this.otherAcronyms = new ArrayList<>();
        this.otherNames = new ArrayList<>();
    }*/

    public List<String> getOtherAcronyms() {
        if (otherAcronyms == null) {
            otherAcronyms = new ArrayList<>();
        }
        return otherAcronyms;
    }

    public List<String> getOtherNames() {
        if (otherNames == null) {
            otherNames = new ArrayList<>();
        }
        return otherNames;
    }

    public boolean isAlternativeName(String name) {
        if(this.name.equalsIgnoreCase(name) || this.acronym.equalsIgnoreCase(name))
            return true;
        for (String otherName : getOtherNames()) {
            if (otherName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        for (String otherAcronym : getOtherAcronyms()) {
            if (otherAcronym.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
