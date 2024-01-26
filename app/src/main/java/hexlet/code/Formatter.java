package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class Formatter {
    public static Map<String, String> convertToStringMap(Map<String, Object> parseMap) {
        Map<String, String> map = new HashMap<>();
        for (var entry : parseMap.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            map.put(key, String.valueOf(value));
        }
        return map;
    }

    public static String mapToString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append("\n");
            stringBuilder.append(entry.getKey()).append(entry.getValue());
        }
        stringBuilder.append("\n").append("}");

        return stringBuilder.toString();
    }

}
