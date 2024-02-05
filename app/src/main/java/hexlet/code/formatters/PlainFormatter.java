package hexlet.code.formatters;

import java.util.Map;

public class PlainFormatter {

    public static String format(Map<String, Map<String, Object>> map) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {

            stringBuilder.append(addDiffString(entry));
        }
        return stringBuilder.toString();
    }


    public static String addDiffString(Map.Entry<String, Map<String, Object>> entry) {
        StringBuilder stringBuilder = new StringBuilder();
        if (entry.getKey().contains("added")) {
            stringBuilder.append("\n" + "Property ");
            stringBuilder.append(convertToStringMapKey(entry.getValue()));
            stringBuilder.append(" was added with value: ");
            stringBuilder.append(convertToStringMapValue(entry.getValue()));
        }
        if (entry.getKey().contains("deleted")) {
            stringBuilder.append("\n" + "Property ");
            stringBuilder.append(convertToStringMapKey(entry.getValue()));
            stringBuilder.append(" was removed");
        }
        if (entry.getKey().contains("changed_map1")) {
            stringBuilder.append("\n" + "Property ");
            stringBuilder.append(convertToStringMapKey(entry.getValue()));
            stringBuilder.append(" was updated. From ").append(convertToStringMapValue(entry.getValue()));
        }
        if (entry.getKey().contains("changed_map2")) {
            stringBuilder.append(" to ").append(convertToStringMapValue(entry.getValue()));
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
