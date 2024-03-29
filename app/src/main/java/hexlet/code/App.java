package hexlet.code;

import picocli.CommandLine;

import java.io.IOException;
import java.util.concurrent.Callable;


public class App {
    @CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true,
            description = "Compares two configuration files and shows a difference.")

    static class GenDiff implements Callable {

        @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
        private String filepath1;
        @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
        private String filepath2;

        @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format",
                description = "output format [default: ${DEFAULT-VALUE}]")
        private String format;

        @Override
        public String call() throws Exception {
            System.out.println(Differ.generate(filepath1, filepath2, format));
            return Differ.generate(filepath1, filepath2, format);
        }
    }

    public static void main(String[] args) throws IOException {
        new CommandLine(new GenDiff()).execute(args);

    }
}
