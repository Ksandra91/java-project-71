package hexlet.code;

import java.util.Objects;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashMap;


public class Diff {
    public static Map<String, Node> findDiff(Map<String, Object> parseMap1,
                                             Map<String, Object> parseMap2) {

        Map<String, Node> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(parseMap1.keySet());
        keys.addAll(parseMap2.keySet());

        for (String key : keys) {
            if (!parseMap1.containsKey(key)) {
                result.put(key, new Node("added", null, parseMap2.get(key)));

            } else if (!parseMap2.containsKey(key)) {
                result.put(key, new Node("deleted", parseMap1.get(key), null));

            } else if (Objects.equals(parseMap1.get(key), parseMap2.get(key))) {
                result.put(key, new Node("unchanged", parseMap2.get(key), parseMap2.get(key)));

            } else {
                result.put(key, new Node("changed", parseMap1.get(key), parseMap2.get(key)));
            }

        }
        return result;

    }
}

