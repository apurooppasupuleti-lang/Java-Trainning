public class Car {

    private int carId;
    private String carName;

    public Car(int carId, String carName) {
        this.carId = carId;
        this.carName = carName;
    }

    public void displayCar() {
        System.out.println("Car ID   : " + carId);
        System.out.println("Car Name : " + carName);
    }
}