package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Differ {

    public static String generate(String format, String filepath1, String filepath2) throws IOException {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        Map<String, String> map1;
        Map<String, String> map2;

        if (format.equals("yaml")) {
            map1 = Parser.parseYaml(filepath1);
            map2 = Parser.parseYaml(filepath2);
        } else {
            String content1 = Files.readString(path1);
            String content2 = Files.readString(path2);
            map1 = Parser.parseJson(content1);
            map2 = Parser.parseJson(content2);
        }

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

        StringBuilder stringBuilder = new StringBuilder("{");

        for (Map.Entry<String, String> entry : result.entrySet()) {
            stringBuilder.append("\n");
            stringBuilder.append(entry.getKey()).append(entry.getValue());
        }
        stringBuilder.append("\n").append("}");

        return stringBuilder.toString();
    }
}
