package eu.fayder.restcountries.testUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonTestUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static boolean jsonEquals(String actualJson, String expectedJson) {
        try {
            JsonNode actual = mapper.readTree(actualJson);
            JsonNode expected = mapper.readTree(expectedJson);
//            System.out.println("\n\n\n\n\nActual JSON: " + actual);
//            System.out.println("\n\n\n\n\nExpected JSON: " + expected);
            return actual.equals(expected);
        } catch (Exception e) {
            throw new RuntimeException("Error comparing JSON", e);
        }
    }

    public static String loadFileContent(String resourcePath) {
        try {
            Path path = Path.of("src/test/resources/" + resourcePath);
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Error reading test file: " + resourcePath, e);
        }
    }
}