package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {

    public static String generate(String format, String filepath1, String filepath2) throws Exception {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);


        Map<String, Object> parse1 = Parser.parse(content1, format);
        Map<String, Object> parse2 = Parser.parse(content2, format);

        Map<String, String> parseMap1 = normalize(parse1);
        Map<String, String> parseMap2 = normalize(parse2);

        Map<String, Map<String, Object>> result = findDiff(parseMap1, parseMap2);


        System.out.println(result);
        return SimpleFormatter.format(result);
    }

    public static Map<String, String> normalize(Map<String, Object> map) {
        Map<String, String> res = new HashMap<>();
        for (var e : map.entrySet()) {
            String key = e.getKey();
            Object valueOb = e.getValue();
            if (valueOb != null) {
                res.put(key, valueOb.toString());
            } else {
                res.put(key, "null");
            }
        }
        return res;
    }

    public static Map<String, Map<String, Object>> findDiff(Map<String, String> parseMap1,
                                                            Map<String, String> parseMap2) {
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(parseMap1.keySet());
        keys.addAll(parseMap2.keySet());
        int id = 0;
        for (String key : keys) {
            if (!parseMap1.containsKey(key)) {
                result.put("added" + id, Map.of(key, parseMap2.get(key)));
            } else if (!parseMap2.containsKey(key)) {
                result.put("deleted" + id, Map.of(key, parseMap1.get(key)));
            } else if (parseMap1.get(key).equals(parseMap2.get(key))) {
                result.put("unchanged" + id, Map.of(key, parseMap2.get(key)));
            } else {
                result.put("changed_map1" + id, Map.of(key, parseMap1.get(key)));
                result.put("changed_map2" + id, Map.of(key, parseMap2.get(key)));
            }
            id++;
        }
        return result;
    }
}
