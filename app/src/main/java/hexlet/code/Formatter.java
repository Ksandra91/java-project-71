package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {

    public static String format(String format, Map<String, Map<String, Object>> result) throws Exception {
        return switch (format) {
            case "stylish" -> StylishFormatter.format(result);
            case "plain" -> PlainFormatter.format(result);
            default -> throw new Exception("Unknown format: '" + format + "'");
        };
    }
}
