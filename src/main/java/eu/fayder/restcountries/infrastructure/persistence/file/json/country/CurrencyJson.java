package eu.fayder.restcountries.infrastructure.persistence.file.json.country;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CurrencyJson {
    private String code;
    private String name;
    private String symbol;
}
