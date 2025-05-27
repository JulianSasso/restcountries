package eu.fayder.restcountries.infrastructure.persistence.file.json.country;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LanguageJson {
    private String iso639_1;
    private String iso639_2;
    private String name;
    private String nativeName;
}
