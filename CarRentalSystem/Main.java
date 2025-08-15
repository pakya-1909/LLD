package CarRentalSystem;
import java.util.*;

enum CarType {
    COMPACT, SEDAN, SUV, TRUCK
}

enum CarState {
    AVAILABLE, BOOKED, MODIFY, CANCEL
}

enum PaymentType{
    UPI, CARD, NET_BANKING
}

interface Payment{
    public void pay();
}

interface CarSearch{
    public List<Car> search(List<Car> carList);
}

interface ReservationState{
    public void availableState(ReservationService reservationService) throws Exception;
    public void bookedState(ReservationService reservationService) throws Exception;
    public void modifyState(ReservationService reservationService) throws Exception;
    public void cancelState(ReservationService reservationService) throws Exception;
}

class UPIPayment implements Payment{

    @Override
    public void pay() {
        System.out.println("paying using UPI");
    }
}

class CardPayment implements Payment{
    @Override
    public void pay() {
        System.out.println("paying using credit or debit card");
    }
}

class NetBankingPayment implements Payment{

    @Override
    public void pay() {
        System.out.println("paying using net-banking");
    }
}

class PaymentObject{
    PaymentType paymentType;

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Payment selectPayment(){
        if(paymentType.equals(PaymentType.CARD)){
            return new CardPayment();
        }
        if(paymentType.equals(PaymentType.UPI)){
            return new UPIPayment();
        }
        if(paymentType.equals(PaymentType.NET_BANKING)){
            return new NetBankingPayment();
        }
        return null;
    }
}

class PaymentService{
    PaymentObject paymentObject;

    public void setPaymentType(PaymentType paymentType){
        paymentObject.setPaymentType(paymentType);
    }

    public void pay(){
        Payment payment = paymentObject.selectPayment();
        payment.pay();
    }
}

class AvailableState implements ReservationState{

    @Override
    public void availableState(ReservationService reservationService) {
        // actual logic here
        System.out.println("car is in available state and now setting the state to booked state");
        reservationService.setState(new BookedState());
    }

    @Override
    public void bookedState(ReservationService reservationService) throws Exception {
        throw new Exception("this state is not allowed");
    }

    @Override
    public void modifyState(ReservationService reservationService) throws Exception {
        throw new Exception("this state is not allowed");
    }

    @Override
    public void cancelState(ReservationService reservationService) throws Exception {
        throw new Exception("this state is not allowed");
    }
}

class BookedState implements ReservationState{

    @Override
    public void availableState(ReservationService reservationService) throws Exception {
        throw new Exception("state not allowed");
    }

    @Override
    public void bookedState(ReservationService reservationService) throws Exception {
        // actual logic here
        System.out.println("car is in booked statw");
        reservationService.setState(new ModifyState());
    }

    @Override
    public void modifyState(ReservationService reservationService) throws Exception {
        throw new Exception("state not allowed");
    }

    @Override
    public void cancelState(ReservationService reservationService) throws Exception {
        throw new Exception("state not allowed");
    }
}

class ModifyState implements ReservationState{

    @Override
    public void availableState(ReservationService reservationService) throws Exception {
        throw new Exception("state now allowed");
    }

    @Override
    public void bookedState(ReservationService reservationService) throws Exception {
        throw new Exception("state now allowed");
    }

    @Override
    public void modifyState(ReservationService reservationService) throws Exception {
        // actual logic here
        System.out.println("car is in modify state");
        reservationService.setState(new CancelState());
    }

    @Override
    public void cancelState(ReservationService reservationService) throws Exception {
        throw new Exception("state now allowed");
    }
}

class CancelState implements ReservationState{

    @Override
    public void availableState(ReservationService reservationService) throws Exception {
        throw new Exception("state now allowed");
    }

    @Override
    public void bookedState(ReservationService reservationService) throws Exception {
        throw new Exception("state now allowed");
    }

    @Override
    public void modifyState(ReservationService reservationService) throws Exception {
        throw new Exception("state now allowed");
    }

    @Override
    public void cancelState(ReservationService reservationService) throws Exception {
        // actual logic here
        System.out.println("car is in cancel state");
        reservationService.setState(new AvailableState());
    }
}

class ReservationService{
    ReservationState currentState;

    public ReservationService(){
        this.currentState = new AvailableState();
    }

    public void setState(ReservationState state){
        this.currentState = state;
    }

    public void bookState() throws Exception {
        currentState.bookedState(this);
    }

    public void modifyState() throws Exception {
        currentState.modifyState(this);
    }

    public void cancelState() throws Exception {
        currentState.cancelState(this);
    }
}

class Car{
    Integer carId;
    String licenseNumber;
    String carModel;
    Integer pricePerDay;
    CarType carType;
    Date rentStartDate;
    Date rentEndDate;
    CarState isAvailable;

    public Car(Integer cardId, String licenseNumber, String carModel, Integer pricePerDay, CarType carType){
        this.carId = cardId;
        this.carModel = carModel;
        this.licenseNumber = licenseNumber;
        this.pricePerDay = pricePerDay;
        this.isAvailable = CarState.AVAILABLE;
        this.carType = carType;
    }


    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public CarState isAvailable() {
        return isAvailable;
    }

    public void setAvailable(CarState available) {
        isAvailable = available;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }
}

class SearchByType implements CarSearch{
    private CarType type;

    @Override
    public List<Car> search(List<Car> carList) {
        return carList.stream().filter((car) -> car.carType.equals(this.type)).toList();
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

}

class SearchByPrice implements CarSearch{
    Integer lowerPrice;
    Integer higherPrice;

    @Override
    public List<Car> search(List<Car> carList) {
        return carList.stream()
            .filter((car) -> {
                return car.pricePerDay >= this.lowerPrice && car.pricePerDay <= this.higherPrice;
            }).toList();
    }

    public Integer getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(Integer lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public Integer getHigherPrice() {
        return higherPrice;
    }

    public void setHigherPrice(Integer higherPrice) {
        this.higherPrice = higherPrice;
    }
}

class SearchByAvailable implements CarSearch{

    @Override
    public List<Car> search(List<Car> carList) {
        return carList.stream().filter((car) -> car.isAvailable.equals(CarState.AVAILABLE)).toList();
    }
}

class CarSearchService{

    public List<Car> searchByType(CarType type, List<Car> carList){
        SearchByType search = new SearchByType();
        search.setType(type);
        return search.search(carList);
    }

    public List<Car> searchByPrice(List<Car> carList, Integer lower, Integer higher){
        SearchByPrice search = new SearchByPrice();
        search.setLowerPrice(lower);
        search.setHigherPrice(higher);
        return search.search(carList);
    }

    public List<Car> searchByAvailable(List<Car> carList){
        SearchByAvailable search = new SearchByAvailable();
        return search.search(carList);
    }
}

class User{
    String name;
    String mobileNumber;
    String licenseNumber;
    String Address;
}

class CarRentalService{
    List<Car> carList;
    List<User> userList;
    CarSearchService carSearchService;
    PaymentService paymentService;
    ReservationService reservationService;

    public CarRentalService(){
        this.carList = new ArrayList<>();
        this.userList = new ArrayList<>();
        this.carSearchService = new CarSearchService();
        this.paymentService = new PaymentService();
        this.reservationService = new ReservationService();
    }

    public List<Car> searchCar(){
        return carSearchService.searchByAvailable(this.carList);
    }

    public void bookCar(Car car){
        this.paymentService.setPaymentType(PaymentType.UPI);
        this.paymentService.pay();
    }

    public void modifyReservation(){

    }

    public void cancelReservation(){

    }

    public void addCar(Car car){
        this.carList.add(car);
    }

    public void removeCar(Car car){
        this.carList.remove(car);
    }

    public void addUser(User user){
        this.userList.add(user);
    }

    public void removeUser(User user){
        this.userList.remove(user);
    }
}


public class Main {
    public static void main(String[] args) {
        System.out.println("Car rental system");
    }
}


/*
 *   Requirements
 * 1. The car rental system should allow customers to browse and reserve available cars for specific dates.
 * 2. Each car should have details such as make, model, year, license plate number, and rental price per day.
 * 3. Customers should be able to search for cars based on various criteria, such as car type, price range,
 *    and availability.
 * 4. The system should handle reservations, including creating, modifying, and canceling reservations.
 * 5. The system should keep track of the availability of cars and update their status accordingly.
 * 6. The system should handle customer information, including name, contact details, and driver's license information.
 * 7. The system should handle payment processing for reservations.
 * */
