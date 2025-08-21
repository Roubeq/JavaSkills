import java.util.EnumSet;

public class EnumDemo {
    public static void main(String[] args) {
        BasicEnum today = BasicEnum.MONDAY;
        System.out.println("Today is: " + today);

        if (today == BasicEnum.MONDAY) {
            System.out.println("It's Monday!");
        }

        switch (today) {
            case MONDAY:
                System.out.println("Start of work week");
                break;
            case FRIDAY:
                System.out.println("End of work week");
                break;
            default:
                System.out.println("Midweek day");
        }

        System.out.println("\nAll days:");
        for (BasicEnum day : BasicEnum.values()) {
            System.out.println(day);
        }

        System.out.println("\nString representation: " + today.toString());
        BasicEnum anotherDay = BasicEnum.valueOf("TUESDAY");
        System.out.println("Parsed enum: " + anotherDay);

        AdvancedEnum operation = AdvancedEnum.ADD;
        System.out.println("\nOperation: " + operation.getSymbol());
        System.out.println("Result: " + operation.apply(5, 3));

        EnumSet<BasicEnum> weekend = EnumSet.of(BasicEnum.SATURDAY, BasicEnum.SUNDAY);
        System.out.println("\nWeekend days: " + weekend);
    }
}