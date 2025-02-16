import java.util.Arrays;

abstract class Automobile implements Comparable<Automobile> {
    protected int modelYear;
    protected String brand;
    
    public Automobile(int modelYear, String brand) {
        this.modelYear = modelYear;
        this.brand = brand;
    }
    
    public void honk() { System.out.println(brand + " honks: Beep Beep!"); }
    public void accelerate() { System.out.println(brand + " is accelerating."); }
    public void stop() { System.out.println(brand + " has stopped."); }
    public void reverse() { System.out.println(brand + " is reversing."); }
    
    public int compareTo(Automobile other) { return Integer.compare(this.modelYear, other.modelYear); }
    public String toString() { return "Brand: " + brand + ", Model Year: " + modelYear; }
    public boolean equals(Object obj) {
        if (obj instanceof Automobile) {
            Automobile other = (Automobile) obj;
            if (modelYear == other.modelYear) {
                if (brand.equals(other.brand)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Car extends Automobile { public Car(int modelYear, String brand) { super(modelYear, brand); } }
class SportsCar extends Automobile { 
    public SportsCar(int modelYear, String brand) { super(modelYear, brand); }
    public void accelerate() { System.out.println(brand + " accelerates rapidly!"); }
}
class JunkCar extends Automobile { 
    public JunkCar(int modelYear, String brand) { super(modelYear, brand); }
    public void accelerate() { System.out.println(brand + " struggles to accelerate."); }
}

public class Tester {
    public static void main(String[] args) {
        Automobile[] automobiles = {
            new Car(2020, "Toyota"),
            new SportsCar(2022, "Ferrari"),
            new JunkCar(1998, "Old Chevy"),
            new Car(2018, "Honda"),
            new SportsCar(2023, "Lamborghini")
        };
        
        for (Automobile auto : automobiles) {
            System.out.println(auto);
            auto.honk();
            auto.accelerate();
            auto.reverse();
            auto.stop();
            System.out.println();
        }
        
        Arrays.sort(automobiles);
        System.out.println("After sorting by model year:");
        for (Automobile auto : automobiles) {
            System.out.println(auto);
        }
    }
}
