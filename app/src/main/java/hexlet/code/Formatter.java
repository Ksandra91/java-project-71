package hexlet.code;

import hexlet.code.formatters.JSONFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {

    public static String format(String format, Map<String, Node> result) throws Exception {
        return switch (format) {
            case "stylish", "" -> StylishFormatter.format(result);
            case "plain" -> PlainFormatter.format(result);
            case "json" -> JSONFormatter.format(result);
            default -> throw new Exception("Unknown format: '" + format + "'");
        };
    }
}
