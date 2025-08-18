public class Car extends Vehicle {
    //incapsulation
    public Car(String model) {
        super(model);
    }

    public String getModel() {
        return this.model;
    }

    public void startEngine() {
        System.out.println("Car " + model+ " started");
    }

    public void startEngine(String peregruz) {
        System.out.println("Car " + model + " "+ peregruz + " started");
    }


    public void setModel(String model) {
        this.model = model;
    }

    @Override                   // polymorphism(runtime)
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                '}';
    }
}

