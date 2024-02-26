package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.Map;

public class PlainFormatter {

    public static String format(Map<String, Node> map) {

        String result = "";
        for (Map.Entry<String, Node> entry : map.entrySet()) {
            result += addDiffString(entry);
        }
        return result.strip();
    }


    public static String addDiffString(Map.Entry<String, Node> entry) {
        String key = entry.getKey();
        String formattedValue1 = convertToStringNodeValue(entry.getValue().newValue);
        String formattedValue2 = convertToStringNodeValue(entry.getValue().oldValue);
        String statusName = entry.getValue().statusName;


        switch (statusName) {
            case "added":
                return "Property '" + key + "' was added"
                        + " with value: " + formattedValue1 + "\n";
            case "deleted":
                return "Property '" + key + "' was removed" + "\n";
            case "changed":
                return "Property '" + key + "' was updated."
                        + " From " + formattedValue2
                        + " to " + formattedValue1 + "\n";
            case "unchanged":
                return "";
            default:
                throw new RuntimeException("Unknown node type: '" + statusName + "'");
        }
    }

    public static String convertToStringNodeValue(Object value) {
        String result = "";
        if (value instanceof Integer) {
            result = value.toString();
        } else if (value instanceof Boolean) {
            result = value.toString();
        } else if (value instanceof String) {
            result = "'" + value + "'";
        } else if (value == null) {
            result = "null";
        } else {
            result = "[complex value]";
        }

        return result;
    }

}
