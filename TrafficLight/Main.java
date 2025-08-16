package TrafficLight;

interface TrafficLightState{
    public void handleRequest(TrafficLightController trafficLightController);
    public void getColor();
}

class GreenLight implements  TrafficLightState{

    @Override
    public void handleRequest(TrafficLightController trafficLightController) {
        System.out.println("Traffic light is green");
        trafficLightController.setState(new YellowLight());
    }

    @Override
    public void getColor() {
        System.out.println("Green");
    }
}

class YellowLight implements TrafficLightState{

    @Override
    public void handleRequest(TrafficLightController trafficLightController) {
        System.out.println("traffic light is yellow");
        trafficLightController.setState(new RedLight());
    }

    @Override
    public void getColor() {
        System.out.println("Yellow");
    }
}

class RedLight implements TrafficLightState{

    @Override
    public void handleRequest(TrafficLightController trafficLightController) {
        System.out.println("traffic light is red");
        trafficLightController.setState(new GreenLight());
    }

    @Override
    public void getColor() {
        System.out.println("Red");
    }
}

class TrafficLightController{
    TrafficLightState currentState;

    public TrafficLightController(){
        this.currentState = new GreenLight();
    }

    public void setState(TrafficLightState state){
        this.currentState = state;
    }

    public void handleRequest(){
        this.currentState.handleRequest(this);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("traffic controller system");

        TrafficLightController trafficLightController = new TrafficLightController();
        for(int i=0; i < 6; i++){
            trafficLightController.handleRequest();
        }
    }
}
