package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    // Побочные эффекты правильно делать не на уровне класса, а внутри хуков
    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }


    @Test
    public void testJsonToStylishFormat() throws Exception {
        String actual = Differ.generate("stylish", "src/test/resources/testfile11.json",
                "src/test/resources/testfile22.json");
        System.out.println(actual);
        assertEquals(resultStylish, actual);
    }

    @Test
    public void testJsonToPlainFormat() throws Exception {
        String actual = Differ.generate("plain", "src/test/resources/testfile11.json",
                "src/test/resources/testfile22.json");
        System.out.println(actual);
        assertEquals(resultPlain, actual);
    }

    @Test
    public void testJsonToJsonFormat() throws Exception {
        String actual = Differ.generate("json", "src/test/resources/testfile11.json",
                "src/test/resources/testfile22.json");
        System.out.println(actual);
        assertEquals(resultJson, actual);
    }

    @Test
    public void testJsonToDefaultFormat() throws Exception {
        String actual = Differ.generate("", "src/test/resources/testfile11.json",
                "src/test/resources/testfile22.json");
        System.out.println(actual);
        assertEquals(resultStylish, actual);
    }

    @Test
    public void testYmlToStylishFormat() throws Exception {
        String actual = Differ.generate("stylish", "src/test/resources/testYaml11.yml",
                "src/test/resources/testYaml22.yml");
        System.out.println(actual);
        assertEquals(resultStylish, actual);
    }

    @Test
    public void testYmlToPlainFormat() throws Exception {
        String actual = Differ.generate("plain", "src/test/resources/testYaml11.yml",
                "src/test/resources/testYaml22.yml");
        System.out.println(actual);
        assertEquals(resultPlain, actual);
    }

    @Test
    public void testYmlToJsonFormat() throws Exception {
        String actual = Differ.generate("json", "src/test/resources/testYaml11.yml",
                "src/test/resources/testYaml22.yml");
        System.out.println(actual);
        assertEquals(resultJson, actual);
    }

    @Test
    public void testYmlToDefaultFormat() throws Exception {
        String actual = Differ.generate("", "src/test/resources/testYaml11.yml",
                "src/test/resources/testYaml22.yml");
        System.out.println(actual);
        assertEquals(resultStylish, actual);
    }

}
