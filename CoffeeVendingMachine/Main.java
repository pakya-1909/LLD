package CoffeeVendingMachine;

interface Coffee{
    public String getDescription();
    public int getCost();
}

class ClassicCoffee implements  Coffee{

    @Override
    public String getDescription() {
        return "Classic Coffee";
    }

    @Override
    public int getCost() {
        return 20;
    }
}

class EspressoCoffee implements Coffee{

    @Override
    public String getDescription() {
        return "Espresso Coffee";
    }

    @Override
    public int getCost() {
        return 30;
    }
}

class CappuccinoCoffee implements Coffee{

    @Override
    public String getDescription() {
        return "Cappuccino Coffee";
    }

    @Override
    public int getCost() {
        return 40;
    }
}

class AlmondMilk implements Coffee{
    Coffee coffee;

    public AlmondMilk(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return "Almond Milk";
    }

    @Override
    public int getCost() {
        return this.coffee.getCost() + 30;
    }
}

class SoyaMilk implements Coffee{
    Coffee coffee;

    public SoyaMilk(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return "Soya Milk";
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 10;
    }
}

class Cream implements Coffee{
    Coffee coffee;

    public Cream(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return "Cream";
    }

    @Override
    public int getCost() {
        return this.coffee.getCost() + 40;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("coffee vending machine");
        int coffeeCost = new Cream(new AlmondMilk(new EspressoCoffee())).getCost();
        System.out.println(coffeeCost);
    }
}
