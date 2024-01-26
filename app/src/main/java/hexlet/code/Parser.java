package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.HashMap;
import java.util.Map;

public class Parser {


    private static Map parseYaml(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        if (content.isEmpty()) {
            return new HashMap();
        } else {
            return mapper.readValue(content, Map.class);
        }
    }

    private static Map parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        if (content.isEmpty()) {
            return new HashMap();
        } else {
            return mapper.readValue(content, Map.class);
        }
    }

    public static Map parse(String content, String dataFormat) throws Exception {
        switch (dataFormat) {
            case "yml":
            case "yaml":
                return parseYaml(content);
            case "":
                return parseJson(content);
            default:
                throw new Exception("Unknown format: '" + dataFormat + "'");
        }
    }
}
