import java.util.*;
import java.util.function.*;

public class MethodReferences {

    public static void main(String[] args) {
        System.out.println("=== Ссылки на методы и конструкторы ===\n");

        demoMethodReferences();
        demoConstructorReferences();
        demoInstanceMethodReferences();
    }

    private static void demoMethodReferences() {
        System.out.println("1. Ссылки на статические методы:");

        Function<String, Integer> lambdaParser = s -> Integer.parseInt(s);

        Function<String, Integer> methodRefParser = Integer::parseInt;

        System.out.println("Лямбда: " + lambdaParser.apply("123"));
        System.out.println("Ссылка: " + methodRefParser.apply("456"));
        System.out.println();
    }

    private static void demoConstructorReferences() {
        System.out.println("2. Ссылки на конструкторы:");

        Supplier<List<String>> lambdaList = () -> new ArrayList<>();

        Supplier<List<String>> constructorRefList = ArrayList::new;

        List<String> list1 = lambdaList.get();
        List<String> list2 = constructorRefList.get();

        list1.add("Лямбда");
        list2.add("Конструктор");

        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);
        System.out.println();
    }

    private static void demoInstanceMethodReferences() {
        System.out.println("3. Ссылки на методы экземпляра:");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        names.forEach(System.out::println);

        Function<String, Integer> lengthGetter = String::length;
        System.out.println("Длины: " + names.stream().map(lengthGetter).toList());
        System.out.println();
    }
}