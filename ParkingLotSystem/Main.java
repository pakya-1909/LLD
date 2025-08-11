package ParkingLotSystem;

import java.util.*;

enum VehicleType {
    CAR, BIKE, TRUCK
}

abstract class Vehicle {
    public String license;
    public VehicleType vehicleType;

    public Vehicle(String license, VehicleType type) {
        this.license = license;
        this.vehicleType = type;
    }
}

class Car extends Vehicle {
    public Car(String license, VehicleType type) {
        super(license, type);
    }
}

class Bike extends Vehicle {

    public Bike(String license, VehicleType type) {
        super(license, type);
    }
}

class Truck extends Vehicle {

    public Truck(String license, VehicleType type) {
        super(license, type);
    }
}

class ParkingSpot {
    int licenseNumber;
    VehicleType parkingSpotType;
    boolean isAvailable;
}

class Level {
    int levelNumber;
    List<ParkingSpot> parkingSpotList = new ArrayList<>();

    public void assignSpot() {

    }

    public void freeSpot() {

    }
}

class Ticket{
    int ticketId;
    ParkingSpot parkingSpot;
    int licenseNumber;
    int enterTime;
    VehicleType vehicleType;
    Entry entryGateId;
    Exit exitGateId;
}

class Entry{
    int enterGateId;
}

class Exit{
    int exitGateId;
}

class ParkingSpotManager {
    List<Level> levelList = new ArrayList<>();

    public ParkingSpot searchParkingSpot(VehicleType vehicleType) {
        return null;
    }
}

class ParkingSystem{
    ParkingSpotManager parkingSpotManager = new ParkingSpotManager();
    
    List<Entry> entryList = new ArrayList<>();
    List<Exit> exitList = new ArrayList<>();
    List<Vehicle> vehicleList = new ArrayList<>();

    public Ticket generateTicket(){
        return null;
    }

    public void collectPayment(Ticket ticket){
        
    }
}

public class Main {
    public static void main(String[] args) {

    }
}

/*
 * 
 * 1. The parking lot should have multiple levels, each level with a certain
 * number of parking spots.
 * 2. The parking lot should support different types of vehicles, such as cars,
 * motorcycles, and trucks.
 * 3. Each parking spot should be able to accommodate a specific type of
 * vehicle.
 * 4. The system should assign a parking spot to a vehicle upon entry and
 * release it when the vehicle exits.
 * 5. The system should track the availability of parking spots and provide
 * real-time information to customers.
 * 6. The system should handle multiple entry and exit points and support
 * concurrent access.
 */