import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.List;

public class FileGenerator {
    public static void main (String args[]) {
        List<String> lines = List.of("Hello", "World", "!!!");
        Path path = Paths.get("output.txt");

        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
            System.out.println("File created using NIO (new input/output)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}