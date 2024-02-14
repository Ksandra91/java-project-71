package hexlet.code;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map;

public class Diff {
    public static Map<Map<String, Object>, String> findDiff(Map<String, Object> parse1,
                                                            Map<String, Object> parse2) {
        Map<String, Object> parseMap1 = normalize(parse1);
        Map<String, Object> parseMap2 = normalize(parse2);

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
}

