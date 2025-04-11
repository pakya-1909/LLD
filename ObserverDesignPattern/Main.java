package ObserverDesignPattern;
// alert notification system is also a observer design pattern

import java.util.*;

interface Subject{
    public void addSubscriber(Subscriber subscriber);
    public void removeSubscriber(Subscriber subscriber);
    public void notifySubscriber();
}

interface Subscriber{
    public void updateSubscriber();
}

abstract class Employee{
    String role;

    public Employee(String role){
        this.role = role;
    }
}

class Developer extends Employee implements Subscriber{
    public Developer(String role) {
        super(role);
    }

    @Override
    public void updateSubscriber() {
        System.out.println("developer alert !!!");
    }
}

class Admin extends Employee implements Subscriber{

    public Admin(String role) {
        super(role);
    }

    @Override
    public void updateSubscriber() {
        System.out.println("admin alert !!!");
    }
}

class Log{
    String logType;
    int time;

    public Log(String type, int time){
        this.logType = type;
        this.time = time;
    }
}


class AlertSystem{

    List<Log> errorLogs = new ArrayList<>();
    List<Log> warningLogs = new ArrayList<>();

    AdminAlert adminAlert = new AdminAlert();
    DeveloperAlert developerAlert = new DeveloperAlert();

    public void processLogs(Log log){
        if(log.logType.equals("warning")){
            warningLogs.add(log);
        }
        if(log.logType.equals("error")){
            errorLogs.add(log);
        }
    }

    public void alert(){
        adminAlert.notifySubscriber();
        developerAlert.notifySubscriber();
    }
}


class AdminAlert implements Subject{

    List<Subscriber> employeeList = new ArrayList<>();

    @Override
    public void addSubscriber(Subscriber subscriber) {
        employeeList.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        employeeList.remove(subscriber);
    }

    @Override
    public void notifySubscriber() {
        for(Subscriber emp: employeeList){
            emp.updateSubscriber();
        }
    }
}


class DeveloperAlert implements Subject{
    List<Subscriber> employeeList = new ArrayList<>();

    @Override
    public void addSubscriber(Subscriber subscriber) {
        employeeList.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        employeeList.remove(subscriber);
    }

    @Override
    public void notifySubscriber() {
        for(Subscriber emp: employeeList){
            emp.updateSubscriber();
        }
    }
}



public class Main {
    public static void main(String[] args) {
        // Create the Alert System
        AlertSystem alertSystem = new AlertSystem();

        // Create Subscribers
        Developer dev1 = new Developer("Frontend Dev");
        Developer dev2 = new Developer("Backend Dev");

        Admin admin1 = new Admin("System Admin");
        Admin admin2 = new Admin("Network Admin");

        // Add Subscribers to their respective alerts
        alertSystem.developerAlert.addSubscriber(dev1);
        alertSystem.developerAlert.addSubscriber(dev2);

        alertSystem.adminAlert.addSubscriber(admin1);
        alertSystem.adminAlert.addSubscriber(admin2);

        // Simulate log entries
        System.out.println("Processing warning logs (Developer Alerts):");
        for (int i = 1; i <= 3; i++) {
            alertSystem.processLogs(new Log("warning", i));
        }

        System.out.println("Processing error logs (Admin Alerts):");
        for (int i = 1; i <= 2; i++) {
            alertSystem.processLogs(new Log("error", i));
        }

        // Trigger the alerts
        System.out.println("\nTriggering alerts:");
        alertSystem.alert();
    }
}
