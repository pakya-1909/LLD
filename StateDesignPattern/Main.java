package StateDesignPattern;


/*
    in state design pattern we set the state of the machine and then change the state of the machine
    according to the step taken by the consumer
 */


interface VendingState {
    void insertCoin(VendingMachine machine);
    void selectProduct(VendingMachine machine, String product);
    void dispenseProduct(VendingMachine machine);
}


class IdleState implements VendingState {
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin inserted.");
        machine.setState(new HasCoinState());
    }

    public void selectProduct(VendingMachine machine, String product) {
        System.out.println("Insert coin first.");
    }

    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Insert coin and select product first.");
    }
}

class HasCoinState implements VendingState {
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin already inserted.");
    }

    public void selectProduct(VendingMachine machine, String product) {
        System.out.println("Product " + product + " selected.");
        machine.setSelectedProduct(product);
        machine.setState(new DispensingState());
    }

    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Select product first.");
    }
}


class DispensingState implements VendingState {
    public void insertCoin(VendingMachine machine) {
        System.out.println("Wait! Dispensing in progress.");
    }

    public void selectProduct(VendingMachine machine, String product) {
        System.out.println("Already selected product: " + machine.getSelectedProduct());
    }

    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Dispensing " + machine.getSelectedProduct() + "...");
        machine.setState(new IdleState());
    }
}

class VendingMachine {
    private VendingState state;
    private String selectedProduct;

    public VendingMachine() {
        state = new IdleState();
    }

    public void setState(VendingState state) {
        this.state = state;
    }

    public void insertCoin() {
        state.insertCoin(this);
    }

    public void selectProduct(String product) {
        state.selectProduct(this, product);
    }

    public void dispenseProduct() {
        state.dispenseProduct(this);
    }

    public void setSelectedProduct(String product) {
        this.selectedProduct = product;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }
}


public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        // machine is in idle state
        machine.insertCoin(); // after running this line the machine will be in has money state
        machine.selectProduct("Coke"); // and after this line the machine will be in dispense state
        machine.dispenseProduct(); // after this machine will be in idle state

        System.out.println("---");

        machine.insertCoin();
        machine.selectProduct("Chips");
        machine.dispenseProduct();
    }
}

