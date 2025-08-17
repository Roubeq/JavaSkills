interface ElectricCar {
    void charge();
}


public class Tesla extends Car implements ElectricCar {
    private String type;

    public Tesla(String model, String type) {
        super(model);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public void startEngine() {
        super.startEngine();
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setModel(String model) {
        super.setModel(model);
    }

    @Override                   // polymorphism(runtime)
    public String toString() {
        return "Tesla{" +
                "type='" + type + '\'' +
                "model='" + super.getModel() + '\'' +
                '}';
    }

    @Override
    public void charge() {
        System.out.println("Charging...");
    }
}
