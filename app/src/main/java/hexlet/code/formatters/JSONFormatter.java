package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JSONFormatter {

    public static String format(Map<Map<String, Object>, String> map) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(map);
        return json;
    }
}
