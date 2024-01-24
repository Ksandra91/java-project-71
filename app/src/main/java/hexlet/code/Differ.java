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

    public static String generate(String filepath1, String filepath2) throws IOException {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> json1 = mapper.readValue(content1, new TypeReference<>() {
        });
        Map<String, String> json2 = mapper.readValue(content2, new TypeReference<>() {
        });

        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(json1.keySet());
        keys.addAll(json2.keySet());

        for (String key : keys) {

            if (!json1.containsKey(key)) {
                result.put("  + " + key, ": " + json2.get(key));
            } else if (!json2.containsKey(key)) {
                result.put("  - " + key, ": " + json1.get(key));
            } else if (json1.get(key).equals(json2.get(key))) {
                result.put("    " + key, ": " + json2.get(key));
            } else {
                result.put("  - " + key, ": " + json1.get(key));
                result.put("  + " + key, ": " + json2.get(key));
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
