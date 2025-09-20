package Elevator;

import java.util.*;

enum Direction{
    UP, DOWN, IDLE
}

enum ElevatorState{
    IDLE, MOVING
}

interface ElevatorDispatchStrategy {
    ElevatorCar selectElevator(List<ElevatorCar> elevators, Request request);
}

class DefaultDispatchStrategy implements ElevatorDispatchStrategy {
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, Request request) {
        ElevatorCar bestElevatorCar = null;
        int mn = Integer.MAX_VALUE;
        for(ElevatorCar car: elevators){
            int diff = Math.abs(car.floor - request.floor);
            if(diff < mn){
                mn = diff;
                bestElevatorCar = car;
            }
        }
        return bestElevatorCar;
    }
}

class Request{
    int floor;
    public Request(int floor) {
        this.floor = floor;
    }
}

class InternalRequest extends Request{

    public InternalRequest(int floor) {
        super(floor);
    }
}

class ExternalRequest extends Request{
    Direction direction;
    public ExternalRequest(int floor, Direction direction) {
        super(floor);
        this.direction = direction;
    }
}

class ElevatorCar{
    int elevatorId;
    int maxCapacity;
    int floor;
    Direction currDirection;
    ElevatorState currState;
    PriorityQueue<Integer> upRequest;
    PriorityQueue<Integer> downRequest;

    public ElevatorCar(int elevatorId, int maxCapacity){
        this.elevatorId = elevatorId;
        this.maxCapacity = maxCapacity;
        this.currDirection = Direction.IDLE;
        this.currState = ElevatorState.IDLE;
        upRequest = new PriorityQueue<>();
        downRequest = new PriorityQueue<>();
    }

    public void submitExternalRequest(ExternalRequest request){
        if(request.direction == Direction.UP) {
            upRequest.offer(request.floor);
        }
        if(request.direction == Direction.DOWN){
            downRequest.offer(request.floor);
        }

    }

    public void submitInternalRequest(InternalRequest request){
        if(request.floor < this.floor){
            downRequest.offer(request.floor);
        }else{
            upRequest.offer(request.floor);
        }
    }

    public void processNextStop(){
        if(this.currDirection == Direction.UP){
            if(!upRequest.isEmpty()){
                this.floor = upRequest.poll();
            }else{
                this.currDirection = Direction.DOWN;
            }
        }else if(this.currDirection == Direction.DOWN){
            if(!downRequest.isEmpty()){
                this.floor = downRequest.poll();
            }else{
                this.currDirection = Direction.UP;
            }
        }else{
            System.out.println("the elevator is idle");
        }
    }

}

class ElevatorSystem{
    List<ElevatorCar> elevatorCarList;
    ElevatorDispatchStrategy elevatorDispatchStrategy;

    public ElevatorSystem(List<ElevatorCar> elevatorCarList, ElevatorDispatchStrategy elevatorDispatchStrategy){
        this.elevatorCarList = elevatorCarList;
        this.elevatorDispatchStrategy = elevatorDispatchStrategy;
    }

    public void submitRequest(ExternalRequest request){
        ElevatorCar selectedElevator = elevatorDispatchStrategy.selectElevator(elevatorCarList, request);
        selectedElevator.submitExternalRequest(request);
    }
}

public class Elevator {
    public static void main(String[] args) {
        System.out.println("elevator LLD");

        List<ElevatorCar> elevatorCarList = new ArrayList<>();
        ElevatorCar elevatorCar1 = new ElevatorCar(123,12);
        ElevatorCar elevatorCar2 = new ElevatorCar(234,10);
        ElevatorCar elevatorCar3 = new ElevatorCar(345,8);
        elevatorCarList.add(elevatorCar1);
        elevatorCarList.add(elevatorCar2);
        elevatorCarList.add(elevatorCar3);

        ElevatorSystem system = new ElevatorSystem(elevatorCarList, new DefaultDispatchStrategy());

        system.submitRequest(new ExternalRequest(2, Direction.UP));
        system.submitRequest(new ExternalRequest(19, Direction.DOWN));

    }
}
