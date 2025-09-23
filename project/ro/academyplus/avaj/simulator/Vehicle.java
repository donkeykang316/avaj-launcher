package ro.academyplus.avaj.simulator;

public class Vehicle {
    private String type;
    private String name;
    private int id;
    private int longtitude;
    private int latitude;
    private int height;

    public Vehicle () {}

    // getter
    public String getType() {return this.type;}
    public String getName() {return this.name;}
    public int getID() {return this.id;};
    public int getLongtitude() {return this.longtitude;}
    public int getLatitude() {return this.latitude;}
    public int getHeight() {return this.height;}

    //setter
    public void setType(String type) {this.type = type;}
    public void setName(String Name) {this.name = Name;}
    public void setID(int id) {this.id = id;}
    public void setLongtitude(int longtitude) {this.longtitude = longtitude;}
    public void setLatitude(int latitude) {this.latitude = latitude;}
    public void setHeight(int height) {this.height = height;}
}