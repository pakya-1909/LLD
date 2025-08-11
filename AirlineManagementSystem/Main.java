// search by source, destination, date
// user book ticket, select seat, make payment
// system should manage flight schedules, aircraft assignments, and crew assignments.
// system should handle passenger information, including personal details and baggage information.
// system should support different types of users, such as passengers, airline staff, and administrators.
// system should be able to handle cancellations, refunds, and flight changes.

package AirlineManagementSystem;

import java.util.*;

class Ticket {
    Flight flight;
    String bookingTime;
    String seatNumber;
}

class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private String date;

    public Flight(String flightId) {
        this.flightNumber = flightId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getDate() {
        return this.date;
    }

    public String getFlightId() {
        return this.flightNumber;
    }
}

interface Search {
    public List<Flight> search(List<Flight> flights);
}

class SearchBySource implements Search {
    public List<Flight> search(List<Flight> flights) {
        return flights;
    }
}

class SearchByDestination implements Search {
    public List<Flight> search(List<Flight> flights) {
        return flights;
    }
}

class SearchByDate implements Search {
    public List<Flight> search(List<Flight> flights) {
        return flights;
    }
}

class SearchStrategy {
    String search;

    public void setSearch(String search) {
        this.search = search;
    }

    public Search searchBy(String type) {

        if (this.search.equals("src")) {
            return new SearchBySource();
        }
        if (this.search.equals("dst")) {
            return new SearchByDestination();
        }
        if (this.search.equals("date")) {
            return new SearchByDate();
        }

        return null;
    }
}

enum UserType {
    PASSENGER, STAFF, ADMINISTRATOR
}

class Luggage {
    Integer weight;
    Integer height;
    Integer width;
    Integer length;
}

class User {
    UserType type;
    String name;
    Luggage luggage;

    public User(UserType type) {
        this.type = type;
    }
}

class Passenger extends User {

    public Passenger(UserType type) {
        super(type);
    }

}

class Staff extends User {

    public Staff(UserType type) {
        super(type);
    }

}

class Administrator extends User {

    public Administrator(UserType type) {
        super(type);
    }

}

class FlightBookingSystem {

    public Ticket bookFlight(Flight flight, User user) {
        return makePayment(10);
    }

    public void cancelFlight(Flight flight, User user) {
        return;
    }

    public Ticket makePayment(Integer amount) {
        return new Ticket();
    }

    public void changeFlight(Flight newFlight, Flight oldFlight, User user) {

    }

    public void flightRefund(Flight flight, User user) {

    }
}

class AirlineManagementSystem {
    List<Flight> flightList;
    FlightBookingSystem flightBookingSystem;
    SearchStrategy searchStrategy;

    public AirlineManagementSystem() {
        this.flightList = new ArrayList<>();
        this.flightBookingSystem = new FlightBookingSystem();
        this.searchStrategy = new SearchStrategy();
    }

    public void addFlight(Flight flight) {
        this.flightList.add(flight);
    }

    public void removeFlight(Flight flight) {
        this.flightList.remove(flight);
    }

    public List<Flight> searchFlight() {
        Search searchBy = searchStrategy.searchBy("src");
        return searchBy.search(flightList);
    }

    public Ticket bookFlight(Flight flight, User user) {
        return flightBookingSystem.bookFlight(flight, user);
    }

    public void cancelFlight(Flight flight, User user) {
        flightBookingSystem.cancelFlight(flight, user);
    }

    public void changeFlight(Flight newFlight, Flight oldFlight, User user) {
        flightBookingSystem.changeFlight(newFlight, oldFlight, user);
    }

    public void flightRefund(Flight flight, User user) {
        flightBookingSystem.flightRefund(flight, user);
    }
}

public class Main {
    public static void main(String[] args) {

    }
}


// perplexity gave me 5/10