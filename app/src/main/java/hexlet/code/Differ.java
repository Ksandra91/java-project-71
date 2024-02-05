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

        Map<String, String> parseMap1 = normalize(parse1);
        Map<String, String> parseMap2 = normalize(parse2);

        Map<String, Map<String, Object>> result = findDiff(parseMap1, parseMap2);


        //System.out.println(result);


        return switch (format) {
            case "stylish" -> StylishFormatter.format(result);
            //case "yml", "yaml" -> SimpleFormatter.format(result);
            // case "json" -> SimpleFormatter.format(result);
            default -> throw new Exception("Unknown format: '" + format + "'");
        };
        // return SimpleFormatter.format(result);
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

    public static String getDataFormat(String filepath) {
        String[] array = filepath.split("\\.");
        return array[1];
    }
}
