package AtmMachine;

import java.util.List;

interface AtmState{
    public void insertCard(AtmMachineService atmMachineService);
    public void enterPin(AtmMachineService atmMachineService, String pin);
    public void withdrawal(AtmMachineService atmMachineService);
    public void checkBalance(AtmMachineService atmMachineService);
    public void ejectCard(AtmMachineService atmMachineService);
}

class NoCardState implements AtmState{

    @Override
    public void insertCard(AtmMachineService atmMachineService) {
        System.out.println("Welcome to atm service");
        atmMachineService.setState(new HasCardState());
    }

    @Override
    public void enterPin(AtmMachineService atmMachineService, String pin) {
        System.out.println("not a valid state");
    }

    @Override
    public void withdrawal(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void checkBalance(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void ejectCard(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }
}

class HasCardState implements  AtmState{

    @Override
    public void insertCard(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void enterPin(AtmMachineService atmMachineService, String pin) {
        System.out.println("Insert card");
        atmMachineService.setState(new EnterPinState());
    }

    @Override
    public void withdrawal(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void checkBalance(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void ejectCard(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }
}

class EnterPinState implements AtmState{

    @Override
    public void insertCard(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void enterPin(AtmMachineService atmMachineService, String pin) {
        System.out.println("please enter your pin");
        if(!atmMachineService.user.getPin().equals(pin)){
            System.out.println("pin invalid");
            return;
        }
        atmMachineService.setState(new QueryState());
    }

    @Override
    public void withdrawal(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void checkBalance(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void ejectCard(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }
}

class QueryState implements AtmState{

    @Override
    public void insertCard(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void enterPin(AtmMachineService atmMachineService, String pin) {
        System.out.println("not a valid state");
    }

    @Override
    public void withdrawal(AtmMachineService atmMachineService) {
        System.out.println("the withdrawal amount");
        atmMachineService.user.setBalance(100);
        atmMachineService.setState(new TerminateState());
    }

    @Override
    public void checkBalance(AtmMachineService atmMachineService) {
        System.out.println("your balance is");
        atmMachineService.setState(new TerminateState());
    }

    @Override
    public void ejectCard(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }
}

class TerminateState implements AtmState{

    @Override
    public void insertCard(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void enterPin(AtmMachineService atmMachineService, String pin) {
        System.out.println("not a valid state");
    }

    @Override
    public void withdrawal(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void checkBalance(AtmMachineService atmMachineService) {
        System.out.println("not a valid state");
    }

    @Override
    public void ejectCard(AtmMachineService atmMachineService) {
        System.out.println("please take you card");
        atmMachineService.setState(new NoCardState());
    }
}

class User{
    String name;
    String id;
    String AccountNumber;
    String pin;
    Integer balance;

    public User(String name, String id, String accountNumber, String pin, Integer balance){
        this.id = id;
        this.name = name;
        this.AccountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}

class AtmMachineService{
    AtmState currentState;
    User user;
    public AtmMachineService(User user){
        this.currentState = new NoCardState();
        this.user = user;
    }

    public void setState(AtmState atmState){
        this.currentState = atmState;
    }

    public void insertCard(){
        this.currentState.insertCard(this);
    }

    public void enterPin(String pin){
        this.currentState.enterPin(this, pin);
    }

    public void queryState(){
        this.currentState.withdrawal(this);
        this.currentState.checkBalance(this);
    }

    public void terminateState(){
        this.currentState.ejectCard(this);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("ATM Machine");
        User user = new User("pankaj", "123", "ABC123", "1234", 100000);
        AtmMachineService atmMachineService = new AtmMachineService(user);
        atmMachineService.insertCard(); // has no card
        atmMachineService.enterPin("1234"); // has card
        atmMachineService.enterPin("1234");
        atmMachineService.queryState();
        atmMachineService.terminateState();
    }
}


/*
1. The ATM system should support basic operations such as balance inquiry, cash withdrawal, and cash deposit.
2. Users should be able to authenticate themselves using a card and a PIN (Personal Identification Number).
3. The system should interact with a bank's backend system to validate user accounts and perform transactions.
4. The ATM should have a cash dispenser to dispense cash to users.
 */