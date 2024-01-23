package hexlet.code;

import picocli.CommandLine;


public class App {
    @CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true,
            description = "Compares two configuration files and shows a difference.")

    static class GenDiff implements Runnable {
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
