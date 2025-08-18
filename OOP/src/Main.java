public class Main {
    public static void main(String[] args) {
        Car car = new Car("Car");
        Tesla tesla = new Tesla("S","Electro");
        System.out.println(car.toString());
        System.out.println(tesla.toString()); // polymorphism(dynamic(runtime))

        car.startEngine("ogo eto vtoraya");
        car.startEngine();  // polymorphism(static(compile time))

        tesla.startEngine();

        tesla.charge();
    }
}


