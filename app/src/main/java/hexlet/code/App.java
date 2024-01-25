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

        @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format",
                description = "output format [default: stylish]")
        private String format;

        @Override
        public String call() throws IOException {
            System.out.println(Differ.generate(format, filepath1, filepath2));
            return Differ.generate(format, filepath1, filepath2);
        }
    }

    public static void main(String[] args) throws IOException {
        new CommandLine(new GenDiff()).execute(args);

    }
}
