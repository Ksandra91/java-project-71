package hexlet.code.formatters;

import java.util.Map;

public class PlainFormatter {

    public static String format(Map<Map<String, Object>, String> map) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Map<String, Object>, String> entry : map.entrySet()) {

            stringBuilder.append(addDiffString(entry));
        }
        return stringBuilder.toString();
    }


    public static String addDiffString(Map.Entry<Map<String, Object>, String> entry) {
        StringBuilder stringBuilder = new StringBuilder();
        if (entry.getValue().contains("added")) {
            stringBuilder.append("\n" + "Property ");
            stringBuilder.append(convertToStringMapKey(entry.getKey()));
            stringBuilder.append(" was added with value: ");
            stringBuilder.append(convertToStringMapValue(entry.getKey()));
        }
        if (entry.getValue().contains("deleted")) {
            stringBuilder.append("\n" + "Property ");
            stringBuilder.append(convertToStringMapKey(entry.getKey()));
            stringBuilder.append(" was removed");
        }
        if (entry.getValue().contains("old value")) {
            stringBuilder.append("\n" + "Property ");
            stringBuilder.append(convertToStringMapKey(entry.getKey()));
            stringBuilder.append(" was updated. From ").append(convertToStringMapValue(entry.getKey()));
        }
        if (entry.getValue().contains("new value")) {
            stringBuilder.append(" to ").append(convertToStringMapValue(entry.getKey()));
        }

        return stringBuilder.toString();
    }

    public static String convertToStringMapKey(Map<String, Object> map) {
        String key = "";

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            key = entry.getKey();
        }
        return "'" + key + "'";
    }

    public static String convertToStringMapValue(Map<String, Object> map) {
        Object value;
        String result = "";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            value = entry.getValue();
            if (value instanceof Integer) {
                result = value.toString();
            } else if (value instanceof Boolean) {
                result = value.toString();
            } else if (value instanceof String) {
                if (value.equals("null")) {
                    result = "null";
                } else {
                    result = "'" + value + "'";
                }
            } else {
                result = "[complex value]";
            }
        }
        return result;
    }
}
