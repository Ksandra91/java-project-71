package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashMap;

public class Differ {

    public static String generate(String format, String filepath1, String filepath2) throws Exception {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);


        Map<String, Object> parseMap1 = Parser.parse(content1, format);
        Map<String, Object> parseMap2 = Parser.parse(content2, format);


        Map<String, String> map1 = Formatter.convertToStringMap(parseMap1);
        Map<String, String> map2 = Formatter.convertToStringMap(parseMap2);


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
        return Formatter.mapToString(result);
    }


}
