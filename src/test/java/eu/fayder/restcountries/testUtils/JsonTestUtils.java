package eu.fayder.restcountries.testUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonTestUtils {

    public static String loadFileContent(String resourcePath) {
        try {
            Path path = Path.of("src/test/resources/" + resourcePath);
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Error reading test file: " + resourcePath, e);
        }
    }
}