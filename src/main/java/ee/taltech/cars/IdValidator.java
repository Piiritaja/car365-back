package ee.taltech.cars;

import java.util.regex.Pattern;

public class IdValidator {
    private final static String GUID_REGEX = "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b";

    public boolean validate(String id) {
        if (id == null) return false;
        return Pattern.compile(GUID_REGEX).matcher(id).matches();
    }
}
