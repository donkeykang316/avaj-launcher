package ro.academyplus.avaj.simulator;

import java.io.IOException;
import ro.academyplus.avaj.simulator.Parser;
import ro.academyplus.avaj.simulator.Vehicle;
import java.util.List;

public class Simulator {
    static String filePath;
    static Parser parser;
    static List<Vehicle> vehicleList;

    public static void main (String args[]) {
        if (args.length != 1) {
            System.out.println("Need one argument!");
            return;
        }
        filePath = args[0].trim();
        if (filePath.isEmpty()) {
            System.out.println("File path is empty!");
            return;
        } else if (!filePath.endsWith(".txt")) {
            System.out.println("File formate incorrect!");
        }

        parser = new Parser(filePath);
        vehicleList = parser.parsing();
        
        int i = vehicleList.size() - 1;
        while (i >= 0)  {
            System.out.println("List " + i + ": " + vehicleList.get(i).getType() + " " + vehicleList.get(i).getName() + " " + vehicleList.get(i).getID());
            i--;
        }
    }
}