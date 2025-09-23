package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.Vehicle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.io.IOException;

public class Parser {
    private static String filePath;
    private static final Set<String> VALID_TYPE = Set.of("JetPlane", "Helicopter", "Balloon", "Baloon");
    private static Set<String> TYPE_NAME = new HashSet<>();
    private static List<Vehicle> vehicleList = new ArrayList<>();
    private static int simulationCycle;
    private static int id = 1;
    private static List<String> registeredVehicle = new ArrayList<>();

    // Constructor
    public Parser(String filePath) {
        this.filePath = filePath;
    }

    public int getSimCycle() {return this.simulationCycle;}
    public void setSimCycle(int simulationCycle) {this.simulationCycle = simulationCycle;}
    public List<String> getRegisteredVehicle() {return this.registeredVehicle;}

    private boolean lineValidator (String line) {
        String[] arr = line.split("\\s+");
        List<String> lineList = Arrays.asList(arr);
        Vehicle vehicle = new Vehicle();
        String type;
        String name;
        String longtitude;
        String latitude;
        String height;

        if (lineList.size() != 5) {
            System.out.println("Line parameters incorrect: " + line);
            return false;
        }

        type = lineList.get(0);
        name = lineList.get(1);
        longtitude = lineList.get(2);
        latitude = lineList.get(3);
        height = lineList.get(4);

        if (!VALID_TYPE.contains(type)) {
            System.out.println("Line shall start with JetPlane, Helicopter or Balloon/Baloon: " + type);
            return false;
        }

        if (type.charAt(0) != name.charAt(0)) {
            System.out.println("Invalid parameters: " + type + " " + name);
            return false;
        }

        if (TYPE_NAME.contains(name)) {
            System.out.println("Dulicate: " + name);
            return false;
        }

        TYPE_NAME.add(name);

        if (!longtitude.matches("\\d+") ||
            !latitude.matches("\\d+") ||
            !height.matches("\\d+")
        ) {
            System.out.println("Invalid parameter: " + longtitude + " " + latitude + " " + height);
            return false;
        }

        int longtitudeInt = Integer.parseInt(longtitude);
        int latitudeInt = Integer.parseInt(latitude);
        int heightInt = Integer.parseInt(height);
        if (heightInt < 0)
            heightInt = 0;
        else if (heightInt > 100)
            heightInt = 100;
        
        vehicle.setType(type);
        vehicle.setName(name);
        vehicle.setID(this.id);
        this.id++;
        vehicle.setLongtitude(longtitudeInt);
        vehicle.setLatitude(latitudeInt);
        vehicle.setHeight(heightInt);

        this.vehicleList.add(vehicle);
        //System.out.println("Tower says: " +
        //    vehicle.getType() +
        //    "#" + vehicle.getName() +
        //    "("+ vehicle.getID() + ") " +
        //    "registered to weather tower."
        //    );
        this.registeredVehicle.add("Tower says: " +
            vehicle.getType() +
            "#" + vehicle.getName() +
            "("+ vehicle.getID() + ") " +
            "registered to weather tower.");      

        return true;
    }

    private boolean fileValidation () {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0 && !line.isEmpty() ) {
                    if (!line.trim().matches("\\d+")) {
                        System.out.println("The iteration line incorrect: " + line);
                        return false;
                    }
                    setSimCycle(Integer.parseInt(line.trim()));
                    i++;
                    continue;
                } else if (!line.isEmpty() && !lineValidator(line)) {
                    return false;
                }
            }
        } catch (IOException e) {
                System.out.println("Erorr:" + e);
                System.exit(1);
        }
        return true;
    }

    public List<Vehicle> parsing() {
        if (!fileValidation())
            System.exit(1);

        return vehicleList;
    }
}