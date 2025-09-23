package ro.academyplus.avaj.simulator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.*;

public class WriteToFile {
    private static Path path = Paths.get("simulation.txt");
    
    public void writeToFile (List<String> content) {
        try {
            Files.write(path, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}