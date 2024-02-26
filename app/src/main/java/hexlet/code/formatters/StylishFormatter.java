package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.Map;

public class StylishFormatter {
    public static String format(Map<String, Node> map) {
        String result = "{";
        for (Map.Entry<String, Node> entry : map.entrySet()) {
            result += ("\n");
            result += addDiffString(entry.getKey(), entry.getValue());
        }
        result += "\n" + "}";
        return result;
    }

    public static String addDiffString(String key, Node node) {

        switch (node.statusName) {
            case "added":
                return "  + " + key + ": " + node.newValue;
            case "deleted":
                return "  - " + key + ": " + node.oldValue;
            case "changed":
                return "  - " + key + ": " + node.oldValue
                        + "\n" + "  + " + key + ": " + node.newValue;
            case "unchanged":
                return "    " + key + ": " + node.newValue;
            default:
                throw new RuntimeException("Unknown status: " + node.statusName);

        }
    }
}


