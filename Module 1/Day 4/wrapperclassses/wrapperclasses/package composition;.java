package composition;

public class CarMain {
    public static void main(String[] args)  {
       Car c = new Car(
                            new Engine(horsePower: 100);
                            new AirConditioner(tons: 1);
                            new MusicSystem(brand: "Sony"));

                 c.getDetails();       
       } 
}