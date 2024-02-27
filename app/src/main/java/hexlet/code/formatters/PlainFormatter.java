package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;
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
        String formattedValue1 = stringify(entry.getValue().getNewValue());
        String formattedValue2 = stringify(entry.getValue().getOldValue());
        String statusName = entry.getValue().getStatusName();


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

    public static String stringify(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }

}
