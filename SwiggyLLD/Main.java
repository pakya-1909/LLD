package SwiggyLLD;

import java.util.*;

interface OrderStates{
    public void orderConfirm(Order order);
    public void orderPreparing(Order order);
    public void orderOutForDelivery(Order order);
    public void orderDelivered(Order order);
}

class OrderPlaced implements OrderStates{

    @Override
    public void orderConfirm(Order order) {
        System.out.println("your order is confirmed");
        order.setOrderState(new OrderPreparing());
    }

    @Override
    public void orderPreparing(Order order) {
        System.out.println("not a valid state");
    }

    @Override
    public void orderOutForDelivery(Order order) {
        System.out.println("not a valid state");
    }

    @Override
    public void orderDelivered(Order order) {
        System.out.println("not a valid state");
    }
}

class OrderPreparing implements  OrderStates{

    @Override
    public void orderConfirm(Order order) {
        System.out.println("not a valid state");
    }

    @Override
    public void orderPreparing(Order order) {
        System.out.println("your order is being prepared");
        order.setOrderState(new OrderOutForDelivery());
    }

    @Override
    public void orderOutForDelivery(Order order) {
        System.out.println("not a valid state");
    }

    @Override
    public void orderDelivered(Order order) {
        System.out.println("not a valid state");
    }
}

class OrderOutForDelivery implements OrderStates{

    @Override
    public void orderConfirm(Order order) {
        System.out.println("not a valid state");
    }

    @Override
    public void orderPreparing(Order order) {
        System.out.println("not a valid state");
    }

    @Override
    public void orderOutForDelivery(Order order) {
        System.out.println("your order is out for delivery");
        order.setOrderState(new OrderDelivered());
    }

    @Override
    public void orderDelivered(Order order) {
        System.out.println("not a valid state");
    }
}

class OrderDelivered implements OrderStates{

    @Override
    public void orderConfirm(Order order) {
        System.out.println("not a valid state");
    }

    @Override
    public void orderPreparing(Order order) {
        System.out.println("not a valid state");
    }

    @Override
    public void orderOutForDelivery(Order order) {
        System.out.println("not a valid state");
    }

    @Override
    public void orderDelivered(Order order) {
        System.out.println("your order is delivered");
    }
}

interface Subject{
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObserver();
}

interface Observer{
    public void updateObserver(OrderStates orderStates);
}

interface PaymentStrategy{
    public void makePayment(PaymentStrategy paymentMethods);
}

class UPIPayment implements PaymentStrategy{
    @Override
    public void makePayment(PaymentStrategy paymentMethods) {

    }
}

class DebitCreditPayment implements PaymentStrategy{
    @Override
    public void makePayment(PaymentStrategy paymentMethods) {

    }
}

class CashOnDeliveryMethod implements PaymentStrategy{
    @Override
    public void makePayment(PaymentStrategy paymentMethods) {

    }
}

class FoodItem{
    private String foodName;
    private int cost;
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public FoodItem(String foodName, int cost, boolean isAvailable){
        this.cost = cost;
        this.foodName = foodName;
        this.isAvailable = isAvailable;
    }

    public void setFoodName(String foodName1){
        this.foodName = foodName1;
    }

    public void setCost(int cost1){
        this.cost = cost1;
    }

    public int getCost() {
        return cost;
    }

    public String getFoodName() {
        return foodName;
    }
}

class Menu{
    List<FoodItem> foodItemList = new ArrayList<>();

    public List<FoodItem> getMenu(){
        return foodItemList;
    }

    public void addFoodItem(FoodItem foodItem){
        foodItemList.add(foodItem);
    }

    public void updateMenu(FoodItem foodItem){
        // for more optimisation we can use map
        String foodName = foodItem.getFoodName();
        for(FoodItem item: foodItemList){
            if(item.getFoodName().equals(foodName)){
                item.setCost(foodItem.getCost());
                item.setAvailable(foodItem.isAvailable());
                break;
            }
        }
    }

    public void removeItem(FoodItem foodItem){
        foodItemList.remove(foodItem);
    }
}

class Order implements Subject{

    List<FoodItem> foodItemList;
    OrderStates orderStates = new OrderPlaced();

    List<Observer> observerList;

    public void addFoodItem(FoodItem foodItem){
        foodItemList.add(foodItem);
    }
    public void removeFoodItem(FoodItem foodItem){
        foodItemList.remove(foodItem);
    }

    public void setOrderState(OrderStates orderStates){
        notifyObserver();
        this.orderStates = orderStates;
    }

    public void placeOrder(Order order){
        this.orderStates = new OrderPlaced();
    }

    public void orderConfirm(Order order){
        this.orderStates.orderConfirm(order);
    }

    public void orderPreparing(Order order){
        this.orderStates.orderPreparing(order);
    }

    public void orderDispatched(Order order){
        this.orderStates.orderOutForDelivery(order);
    }

    public void orderDelivered(Order order){
        this.orderStates.orderDelivered(order);
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer: observerList){
            observer.updateObserver(orderStates);
        }
    }
}

class Location{
    public String city;
    public String state;
    public String zipcode;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}

class Restaurant implements Observer{
    Location location;
    Menu menu = new Menu();

    List<Order> orderList;

    public void placeOrder(Order order){
        orderList.add(new Order());
    }

    public List<FoodItem> getMenu(){
        return this.menu.getMenu();
    }

    public void updateMenu(FoodItem foodItem){
        menu.updateMenu(foodItem);
    }

    public void addFoodItem(FoodItem foodItem){
        menu.addFoodItem(foodItem);
    }

    public void removeFoodItem(FoodItem foodItem){
        menu.removeItem(foodItem);
    }

    @Override
    public void updateObserver(OrderStates orderStates) {

    }
}

class RestaurantController{
    List<Restaurant> restaurantList;

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void addRestaurantList(Restaurant restaurantList) {
        this.restaurantList.add(restaurantList);
    }

    public Restaurant searchRestaurant(String zipcode){
        // implement search functionality
        return new Restaurant();
    }
}

class Consumer implements Observer{
    PaymentStrategy paymentStrategy;
    Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void makePayment(){
        paymentStrategy.makePayment(new UPIPayment());
    }

    @Override
    public void updateObserver(OrderStates orderStates) {
        System.out.println("notified observer");
    }
}

class DeliveryAgent implements Observer{
    Order order;

    public void acceptOrder(){
        System.out.println("delivery agent accept order");
    }


    @Override
    public void updateObserver(OrderStates orderStates) {
        System.out.println("notified delivery agent");
    }
}

class FoodDeliveryService{
    RestaurantController restaurantController = new RestaurantController();
    Consumer consumer = new Consumer();
    DeliveryAgent deliveryAgent = new DeliveryAgent();
    public void startService(){
        Restaurant restaurant = restaurantController.searchRestaurant("12312");
        List<FoodItem> menuFoodItems = restaurant.getMenu();

        Order order = new Order();
        order.addFoodItem(menuFoodItems.get(0));
        order.addFoodItem(menuFoodItems.get(1));
        order.removeFoodItem(menuFoodItems.get(0));

        // setting the state of the order
        order.placeOrder(order);
        order.orderConfirm(order);
        order.orderPreparing(order);
        order.orderDispatched(order);
        order.orderDelivered(order);

        // notification system is also done

    }

}

public class Main {
    public static void main(String[] args) {

    }
}