import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaBasics {
    public static void main(String[] args) {
        oldSyntax();
        newSyntax();
        parametersLambda();
        myInterfaceFunctional();
    }

    public static void oldSyntax() {
        Runnable oldWay = new Runnable() {
            @Override
            public void run() {
                System.out.println("Это старый метод, через анонимный объект");
            }
        };
        oldWay.run();
    }

    public static void newSyntax() {
        Runnable run = () -> System.out.println("Это новый метод, через лямбда-выражения");
        run.run();
    }

    /* тип параметра можно опустить
     * так же можно многострочную лямбду сделать с помощью {}
     */
    public static void parametersLambda() {
        Consumer<String> printer = (String s) -> System.out.println(s);
        printer.accept("короче как то так");
    }

    public static void myInterfaceFunctional() {
        MyInterface myInterface = (String s) -> System.out.println("А вот эта лямбда(нереализованная функция в интерфейсе)" + s);
        myInterface.print("А вот переданный параметр этой лямбде");

        Predicate<Integer> isEven = n -> n % 2 == 0;
        isEven.test(10);
    }


}
