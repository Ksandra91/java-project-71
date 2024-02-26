package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;

import java.util.Map;

public class JSONFormatter {

    public static String format(Map<String, Node> map) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(map);
        return json;
    }
}
