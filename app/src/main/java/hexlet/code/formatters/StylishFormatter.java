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

        switch (node.getStatusName()) {
            case "added":
                return "  + " + key + ": " + node.getNewValue();
            case "deleted":
                return "  - " + key + ": " + node.getOldValue();
            case "changed":
                return "  - " + key + ": " + node.getOldValue()
                        + "\n" + "  + " + key + ": " + node.getNewValue();
            case "unchanged":
                return "    " + key + ": " + node.getNewValue();
            default:
                throw new RuntimeException("Unknown status: " + node.getStatusName());

        }
    }
}


