package hexlet.code;

import java.util.*;

public class SimpleFormatter {
    public static String format(Map<String, Map<String, Object>> map) {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            stringBuilder.append("\n");
            stringBuilder.append(addDiffString(entry.getKey()));
            stringBuilder.append(convertToStringMapKey(entry.getValue()));
            stringBuilder.append(convertToStringMapValue(entry.getValue()));
        }
        stringBuilder.append("\n").append("}");

        return stringBuilder.toString();
    }

    public static String addDiffString(String key) {

        if (key.contains("added") || key.contains("changed_map2")) {
            return "  + ";
        }
        if (key.contains("deleted") || key.contains("changed_map1")) {
            return "  - ";
        }
        if (key.contains("unchanged")) {
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


