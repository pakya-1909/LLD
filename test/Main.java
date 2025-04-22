package test;

// state design pattern --> atm machine

interface AtmState{
    public void idleState(AtmMachine atmMachine);
    public void enterPin(AtmMachine atmMachine);
    public void withdrawalMoney(AtmMachine atmMachine);
    public void resetPin(AtmMachine atmMachine);
    public void miniStatement(AtmMachine atmMachine);
    public void checkBalance(AtmMachine atmMachine);
}

class IdleState implements AtmState{

    @Override
    public void idleState(AtmMachine atmMachine) {
        System.out.println("validating the pin");
    }

    @Override
    public void enterPin(AtmMachine atmMachine) {

    }

    @Override
    public void withdrawalMoney(AtmMachine atmMachine) {

    }

    @Override
    public void resetPin(AtmMachine atmMachine) {

    }

    @Override
    public void miniStatement(AtmMachine atmMachine) {

    }

    @Override
    public void checkBalance(AtmMachine atmMachine) {

    }
}

class SelectServices implements AtmState{

    @Override
    public void idleState(AtmMachine atmMachine) {

    }

    @Override
    public void enterPin(AtmMachine atmMachine) {

    }

    @Override
    public void withdrawalMoney(AtmMachine atmMachine) {

    }

    @Override
    public void resetPin(AtmMachine atmMachine) {

    }

    @Override
    public void miniStatement(AtmMachine atmMachine) {

    }

    @Override
    public void checkBalance(AtmMachine atmMachine) {

    }
}

class TerminateService implements AtmState{

    @Override
    public void idleState(AtmMachine atmMachine) {

    }

    @Override
    public void enterPin(AtmMachine atmMachine) {

    }

    @Override
    public void withdrawalMoney(AtmMachine atmMachine) {

    }

    @Override
    public void resetPin(AtmMachine atmMachine) {

    }

    @Override
    public void miniStatement(AtmMachine atmMachine) {

    }

    @Override
    public void checkBalance(AtmMachine atmMachine) {

    }
}

class AtmMachine{
    AtmState atmState;

    public AtmMachine(){
        this.atmState = new IdleState();
    }

    public AtmState getAtmState(){
        return this.atmState;
    }

    public void setAtmState(AtmState atmState) {
        this.atmState = atmState;
    }

    public void enterPin(){
        System.out.println("please enter the pin");
        atmState.enterPin(this);
    }

    public void checkBalance(){
        atmState.checkBalance(this);
    }

}

public class Main {
    public static void main(String[] args) {

    }
}


// state, observer, strategy, singleton, decorator, factory
