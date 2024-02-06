package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class Differ {

    public static String generate(String format, String filepath1, String filepath2) throws Exception {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        String dataFormat = getDataFormat(filepath1);

        Map<String, Object> parse1 = Parser.parse(content1, dataFormat);
        Map<String, Object> parse2 = Parser.parse(content2, dataFormat);

        Map<String, Object> parseMap1 = normalize(parse1);
        Map<String, Object> parseMap2 = normalize(parse2);

        Map<Map<String, Object>, String> result = findDiff(parseMap1, parseMap2);

        return Formatter.format(format, result);

    }

    public static Map<String, Object> normalize(Map<String, Object> map) {
        Map<String, Object> res = new HashMap<>();
        for (var e : map.entrySet()) {
            String key = e.getKey();
            Object valueOb = e.getValue();
            if (valueOb != null) {
                res.put(key, valueOb);
            } else {
                res.put(key, "null");
            }
        }
        return res;
    }

    public static Map<Map<String, Object>, String> findDiff(Map<String, Object> parseMap1,
                                                            Map<String, Object> parseMap2) {
        Map<Map<String, Object>, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(parseMap1.keySet());
        keys.addAll(parseMap2.keySet());

        for (String key : keys) {
            if (!parseMap1.containsKey(key)) {
                result.put(Map.of(key, parseMap2.get(key)), "added");

            } else if (!parseMap2.containsKey(key)) {
                result.put(Map.of(key, parseMap1.get(key)), "deleted");

            } else if (parseMap1.get(key).equals(parseMap2.get(key))) {
                result.put(Map.of(key, parseMap2.get(key)), "unchanged");

            } else {
                result.put(Map.of(key, parseMap1.get(key)), "old value");
                result.put(Map.of(key, parseMap2.get(key)), "new value");
            }
        }
        return result;
    }

    public static String getDataFormat(String filepath) {
        String[] array = filepath.split("\\.");
        return array[1];
    }
}
