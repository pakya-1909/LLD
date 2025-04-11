package DecoratorDesignPattern;

// coffee price calculation system

interface CoffeeMachineComponents{
    public int getCost();
    public String description();
}


// these are my base classes
class FilterCoffee implements CoffeeMachineComponents{

    int cost = 100;

    @Override
    public int getCost(){
        System.out.println("filter coffee");
        return cost;
    }

    @Override
    public String description() {
        return "filter coffee";
    }
}

class Espresso implements CoffeeMachineComponents{
    int cost = 130;
    @Override
    public int getCost(){
        System.out.println("espresso");
        return cost;
    }

    @Override
    public String description() {
        return "espresso";
    }
}

// these are my decorator classes
abstract class CoffeeDecorator implements CoffeeMachineComponents{
    protected CoffeeMachineComponents coffeeMachineComponents;

    public CoffeeDecorator(CoffeeMachineComponents coffeeMachineComponents){
        this.coffeeMachineComponents = coffeeMachineComponents;
    }

    @Override
    public int getCost() {
        System.out.println("abstract class get-cost called");
        return coffeeMachineComponents.getCost();
    }

    @Override
    public String description(){
        return coffeeMachineComponents.description();
    }
}

class Milk extends CoffeeDecorator{

    public Milk(CoffeeMachineComponents coffeeMachineComponents) {
        super(coffeeMachineComponents);
    }

    @Override
    public int getCost(){
        System.out.println("milk" + " " + coffeeMachineComponents.toString());
        return 70 + super.getCost();
    }

    @Override
    public String description() {
        return "Milk + " + coffeeMachineComponents.description();
    }
}


class Sugar extends CoffeeDecorator{

    public Sugar(CoffeeMachineComponents coffeeMachineComponents) {
        super(coffeeMachineComponents);
    }

    @Override
    public int getCost(){
        System.out.println("sugar" + " " + coffeeMachineComponents.toString());
        return 20 + super.getCost();
    }

    @Override
    public String description() {
        return "Sugar " + coffeeMachineComponents.description();
    }
}

class Cream extends CoffeeDecorator{

    public Cream(CoffeeMachineComponents coffeeMachineComponents) {
        super(coffeeMachineComponents);
    }

    @Override
    public int getCost(){
        System.out.println("cream" + " " + coffeeMachineComponents.toString());
        return 100 + super.getCost();
    }

    @Override
    public String description() {
        return "Cream + " + coffeeMachineComponents.description();
    }
}


public class Main {
    public static void main(String[] args) {
        CoffeeMachineComponents myOrder = new Cream(new Sugar(new Milk(new FilterCoffee())));
        System.out.println(myOrder.getCost());
        System.out.println(myOrder.description());
    }
}
