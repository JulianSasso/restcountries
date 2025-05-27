package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CurrencyDocument {
    private String code;
    private String name;
    private String symbol;
}
