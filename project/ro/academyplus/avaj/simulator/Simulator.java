package ro.academyplus.avaj.simulator;

import java.io.IOException;
import ro.academyplus.avaj.simulator.Parser;
import ro.academyplus.avaj.simulator.Vehicle;
import ro.academyplus.avaj.simulator.WriteToFile;
import java.util.*;
import java.util.Random;

public class Simulator {
    private static String filePath;
    private static Parser parser;
    private static List<Vehicle> vehicleList;
    private static int simulationCycle;
    private static enum WeatherType {
        SUN, RAIN, FOG, SNOW;
    }
    private static WriteToFile toFile = new WriteToFile();

    private static WeatherType weatherGenerator (Vehicle vehicle) {
        int seed = simulationCycle + 31 * (vehicle.getLongtitude() + vehicle.getLatitude() + vehicle.getHeight());
        Random rd = new Random(seed);
        int pick = rd.nextInt(4);
        switch (pick) {
            case 0:
                return WeatherType.SUN;
            case 1:
                return WeatherType.RAIN;
            case 2:
                return WeatherType.FOG;
            case 3:
                return WeatherType.SNOW;
        }
        return null;

    }

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
        simulationCycle = parser.getSimCycle();
        List<String> content = parser.getRegisteredVehicle();
        
        while (simulationCycle >= 0) {
            int i = 0;
            while (i <= vehicleList.size() - 1)  {
                //System.out.println(weatherGenerator(vehicleList.get(i)));
                Vehicle airCraft = vehicleList.get(i);
                WeatherType weathertype = weatherGenerator(airCraft);
                switch (weathertype) {
                    case SUN:
                        if (airCraft.getType().equals("JetPlane")) {
                            airCraft.setLatitude(airCraft.getLatitude() + 10);
                            airCraft.setHeight(airCraft.getHeight() + 2);
                        } else if (airCraft.getType().equals("Helicopter")) {
                            airCraft.setLongtitude(airCraft.getLongtitude() + 10);
                            airCraft.setHeight(airCraft.getHeight() + 2);
                        } else if (airCraft.getType().equals("Balloon") || airCraft.getType().equals("Baloon")) {
                            airCraft.setLongtitude(airCraft.getLongtitude() + 2);
                            airCraft.setHeight(airCraft.getHeight() + 4);
                        }
                        content.add(
                            airCraft.getType() +
                            "#" + airCraft.getName() +
                            "("+ airCraft.getID() + "): " +
                            "I love the sun :)"
                        );
                        break;
                    case RAIN:
                        if (airCraft.getType().equals("JetPlane")) {
                            airCraft.setLatitude(airCraft.getLatitude() + 5);
                        } else if (airCraft.getType().equals("Helicopter")) {
                            airCraft.setLongtitude(airCraft.getLongtitude() + 5);
                        } else if (airCraft.getType().equals("Balloon") || airCraft.getType().equals("Baloon")) {
                            airCraft.setHeight(airCraft.getHeight() + 5);
                        }
                        content.add(
                            airCraft.getType() +
                            "#" + airCraft.getName() +
                            "("+ airCraft.getID() + "): " +
                            "You made me so wet :("
                        );
                        break;
                    case FOG:
                        if (airCraft.getType().equals("JetPlane")) {
                            airCraft.setLatitude(airCraft.getLatitude() + 1);
                        } else if (airCraft.getType().equals("Helicopter")) {
                            airCraft.setLongtitude(airCraft.getLongtitude() + 1);
                        } else if (airCraft.getType().equals("Balloon") || airCraft.getType().equals("Baloon")) {
                            airCraft.setHeight(airCraft.getHeight() - 3);
                        }
                        content.add(
                            airCraft.getType() +
                            "#" + airCraft.getName() +
                            "("+ airCraft.getID() + "): " +
                            "Well, I can't see -_-"
                        );
                        break;
                    case SNOW:
                        if (airCraft.getType().equals("JetPlane")) {
                            airCraft.setHeight(airCraft.getHeight() - 7);
                        } else if (airCraft.getType().equals("Helicopter")) {
                            airCraft.setHeight(airCraft.getHeight() - 12);
                        } else if (airCraft.getType().equals("Balloon") || airCraft.getType().equals("Baloon")) {
                            airCraft.setHeight(airCraft.getHeight() - 15);
                        }
                        content.add(
                            airCraft.getType() +
                            "#" + airCraft.getName() +
                            "("+ airCraft.getID() + "): " +
                            "It's freezing X|"
                        );
                        break;
                }
                if (airCraft.getHeight() <= 0) {
                    content.add(
                        airCraft.getType() +
                        "#" + airCraft.getName() +
                        "("+ airCraft.getID() + ") " +
                        "landing.\n" +
                        "Tower says: " +
                        airCraft.getType() +
                        "#" + airCraft.getName() +
                        "("+ airCraft.getID() + ") " +
                        "unregistered from weather tower."
                    );
                    vehicleList.remove(i);
                    break;
                } else if (airCraft.getHeight() >= 100) {
                    airCraft.setHeight(100);
                }

                i++;
            }
            simulationCycle--;
        }
        toFile.writeToFile(content);
    }
}