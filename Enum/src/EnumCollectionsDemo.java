import java.util.EnumMap;
import java.util.Map;

public class EnumCollectionsDemo {
    public static void main(String[] args) {
        EnumMap<BasicEnum, String> activities = new EnumMap<>(BasicEnum.class);
        activities.put(BasicEnum.MONDAY, "Work");
        activities.put(BasicEnum.SATURDAY, "Relax");

        System.out.println("Activities:");
        for (Map.Entry<BasicEnum, String> entry : activities.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    }
