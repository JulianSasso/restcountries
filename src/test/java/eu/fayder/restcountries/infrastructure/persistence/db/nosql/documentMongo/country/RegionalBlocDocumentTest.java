package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegionalBlocDocumentTest {
    @Test
    void givenRegionalBlocWithPrimaryName_whenCheckingSameName_thenReturnTrue() {
        // Given
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .build();

        // When
        boolean result = bloc.isAlternativeName("European Union");

        // Then
        assertTrue(result);
    }

    @Test
    void givenRegionalBloc_whenCheckingNameWithDifferentCase_thenReturnTrue() {
        // Given
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .build();

        // When
        boolean result = bloc.isAlternativeName("european union");

        // Then
        assertTrue(result);
    }

    @Test
    void givenRegionalBloc_whenCheckingPrimaryAcronym_thenReturnTrue() {
        // Given
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .build();

        // When
        boolean result = bloc.isAlternativeName("EU");

        // Then
        assertTrue(result);
    }

    @Test
    void givenRegionalBloc_whenCheckingPrimaryAcronymWithDifferentCase_thenReturnTrue() {
        // Given
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .build();

        // When
        boolean result = bloc.isAlternativeName("eu");

        // Then
        assertTrue(result);
    }

    @Test
    void givenRegionalBlocWithOtherNames_whenCheckingOtherName_thenReturnTrue() {
        // Given
        List<String> otherNames = List.of("Europe", "Union of Europe");
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .otherNames(otherNames)
                                        .build();

        // When
        boolean result = bloc.isAlternativeName("Europe");

        // Then
        assertTrue(result);
    }

    @Test
    void givenRegionalBlocWithOtherNames_whenCheckingOtherNameWithDifferentCase_thenReturnTrue() {
        // Given
        List<String> otherNames = List.of("Europe", "Union of Europe");
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .otherNames(otherNames)
                                        .build();

        // When
        boolean result = bloc.isAlternativeName("europe");

        // Then
        assertTrue(result);
    }

    @Test
    void givenRegionalBlocWithOtherAcronyms_whenCheckingOtherAcronym_thenReturnTrue() {
        // Given
        List<String> otherAcronyms = List.of("EUR", "EUU");
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .otherAcronyms(otherAcronyms)
                                        .build();

        // When
        boolean result = bloc.isAlternativeName("EUR");

        // Then
        assertTrue(result);
    }

    @Test
    void givenRegionalBlocWithOtherAcronyms_whenCheckingOtherAcronymWithDifferentCase_thenReturnTrue() {
        // Given
        List<String> otherAcronyms = List.of("EUR", "EUU");
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .otherAcronyms(otherAcronyms)
                                        .build();

        // When
        boolean result = bloc.isAlternativeName("eur");

        // Then
        assertTrue(result);
    }

    @Test
    void givenCompleteRegionalBloc_whenCheckingNonExistentName_thenReturnFalse() {
        // Given
        List<String> otherAcronyms = List.of("EUR", "EUU");
        List<String> otherNames = List.of("Europe", "Union of Europe");
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .otherAcronyms(otherAcronyms)
                                        .otherNames(otherNames)
                                        .build();

        // When
        boolean result = bloc.isAlternativeName("Asia");

        // Then
        assertFalse(result);
    }

    @Test
    void givenRegionalBlocWithNullOtherNames_whenCheckingNames_thenReturnExpectedResults() {
        // Given
        List<String> otherAcronyms = List.of("EUR", "EUU");
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .otherAcronyms(otherAcronyms)
                                        
                                        .build();

        // When & Then
        assertTrue(bloc.isAlternativeName("EUR"));
        assertFalse(bloc.isAlternativeName("Asia"));
    }

    @Test
    void givenRegionalBlocWithNullOtherAcronyms_whenCheckingNames_thenReturnExpectedResults() {
        // Given
        List<String> otherNames = List.of("Europe", "Union of Europe");
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        
                                        .otherNames(otherNames)
                                        .build();

        // When & Then
        assertTrue(bloc.isAlternativeName("Europe"));
        assertFalse(bloc.isAlternativeName("EUU"));
    }

    @Test
    void givenRegionalBlocWithNullLists_whenCheckingNames_thenReturnExpectedResults() {
        // Given
        RegionalBlocDocument bloc = RegionalBlocDocument.builder()
                                        .acronym("EU")
                                        .name("European Union")
                                        .build();

        // When & Then
        assertTrue(bloc.isAlternativeName("EU"));
        assertTrue(bloc.isAlternativeName("European Union"));
        assertFalse(bloc.isAlternativeName("EUR"));
        assertFalse(bloc.isAlternativeName("Europe"));
    }
}
