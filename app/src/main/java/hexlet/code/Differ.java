package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        String dataFormat = getDataFormat(filepath1);

        Map<String, Object> parse1 = Parser.parse(content1, dataFormat);
        Map<String, Object> parse2 = Parser.parse(content2, dataFormat);

        Map<String, Node> result = Diff.findDiff(parse1, parse2);

        return Formatter.format(format, result);

    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }


    public static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
