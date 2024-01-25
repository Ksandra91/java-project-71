package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, String> parseYaml(String filePth) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Map <String,String> yml = mapper.readValue(new File(filePth),Map.class);
        return yml;
    }

    public static Map <String,String> parseJson(String fileContent) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> json = mapper.readValue(fileContent, new TypeReference<>() {
        });
        return json;
    }

}
