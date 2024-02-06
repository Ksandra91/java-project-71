package hexlet.code.formatters;

import java.util.Map;

public class StylishFormatter {
    public static String format(Map<Map<String, Object>, String> map) {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Map.Entry<Map<String, Object>, String> entry : map.entrySet()) {
            stringBuilder.append("\n");
            stringBuilder.append(addDiffString(entry.getValue()));
            stringBuilder.append(convertToStringMapKey(entry.getKey()));
            stringBuilder.append(convertToStringMapValue(entry.getKey()));
        }
        stringBuilder.append("\n").append("}");

        return stringBuilder.toString();
    }

    public static String addDiffString(String value) {

        if (value.contains("added") || value.contains("new value")) {
            return "  + ";
        }
        if (value.contains("deleted") || value.contains("old value")) {
            return "  - ";
        }
        if (value.contains("unchanged")) {
            return "    ";
        }
        return "";
    }

    public static String convertToStringMapKey(Map<String, Object> map) {
        String key = "";

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            key = entry.getKey();
        }
        return key + ": ";
    }

    public static String convertToStringMapValue(Map<String, Object> map) {
        String value = "";

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            value = entry.getValue().toString();
        }
        return value;
    }
}


