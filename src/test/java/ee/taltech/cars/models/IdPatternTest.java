package ee.taltech.cars.models;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdPatternTest {
    private final static String GUID_REGEX = "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b";

    @Test
    static void getId(String id) {
        assertTrue(Pattern.compile(GUID_REGEX).matcher(id).matches());
    }
}
