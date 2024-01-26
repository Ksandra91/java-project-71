package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Differ {

    public static String generate(String format, String filepath1, String filepath2) throws Exception {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);


        Map<String, Object> parseMap1 = Parser.parse(content1, format);
        Map<String, Object> parseMap2 = Parser.parse(content2, format);


        Map<String, String> map1 = convertToStringMap(parseMap1);
        Map<String, String> map2 = convertToStringMap(parseMap2);


        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {

            if (!map1.containsKey(key)) {
                result.put("  + " + key, ": " + map2.get(key));
            } else if (!map2.containsKey(key)) {
                result.put("  - " + key, ": " + map1.get(key));
            } else if (map1.get(key).equals(map2.get(key))) {
                result.put("    " + key, ": " + map2.get(key));
            } else {
                result.put("  - " + key, ": " + map1.get(key));
                result.put("  + " + key, ": " + map2.get(key));
            }
        }
        return mapToString(result);
    }

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
