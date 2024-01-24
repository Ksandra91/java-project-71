package hexlet.code;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {
    @Test
    public void testPositive() throws IOException {
        String expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";

        String actual = Differ.generate("/home/aleksandra91/java-project-71/app/src/test/resources/testfile1.json",
                "/home/aleksandra91/java-project-71/app/src/test/resources/testfile2.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testEmpty() throws IOException {
        String expected = "{\n" +
                "  - follow: false\n" +
                "  - host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "}";

        String actual = Differ.generate("/home/aleksandra91/java-project-71/app/src/test/resources/testfile1.json",
                "/home/aleksandra91/java-project-71/app/src/test/resources/testfile3.json");
        assertEquals(expected, actual);
    }

}
