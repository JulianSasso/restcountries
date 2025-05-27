package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LanguageDocument {
    private String iso639_1;
    private String iso639_2;
    private String name;
    private String nativeName;
}
