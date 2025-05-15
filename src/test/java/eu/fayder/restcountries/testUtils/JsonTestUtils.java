package eu.fayder.restcountries.testUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTestUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static boolean jsonEquals(String actualJson, String expectedJson) {
        try {
            JsonNode actual = mapper.readTree(actualJson);
            JsonNode expected = mapper.readTree(expectedJson);
            return actual.equals(expected);
        } catch (Exception e) {
            throw new RuntimeException("Error comparing JSON", e);
        }
    }
}