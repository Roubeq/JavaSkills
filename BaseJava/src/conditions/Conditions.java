package conditions;

public class Conditions {
    public static void main(String[] args) {
        int age = 25;

        // if-else
        if (age >= 18) {
            System.out.println("Взрослый");
        } else {
            System.out.println("Ребёнок");
        }

        // switch-case
        String day = "Monday";
        switch (day) {
            case "Monday": System.out.println("Понедельник"); break;
            default: System.out.println("Другой день");
        }

        // ternar
        String status = (age > 18) ? "Взрослый" : "Ребёнок";
    }
}
