import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        if (args.length != 1) {
                System.out.println("Need only one argument!");
                System.exit(1);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Line: " + line);
            }
        } catch (IOException e) {
            System.out.println("Erorr:" + e);
        }
    }
}