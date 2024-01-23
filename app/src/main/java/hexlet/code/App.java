package hexlet.code;

import picocli.CommandLine;


public class App {
    @CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true,
            description = "Compares two configuration files and shows a difference.")

//

    static class GenDiff implements Runnable {

        @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
        private String filepath1;
        @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
        private String filepath2;

        @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
        private String format;

        @Override
        public void run() {
            //   Differ.generate();
        }
    }

    public static void main(String[] args) {
        new CommandLine(new GenDiff()).execute(args);
        System.out.println("Hello World!");
    }
}
