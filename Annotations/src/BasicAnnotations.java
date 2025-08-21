public class BasicAnnotations {

    @Deprecated
    public static void method() {
        // помечается как устаревший(предупреждение)
    }

    @SuppressWarnings("кароче тут да")
    public static void stringWarnings() {
        // убирает предупреждения
    }

    @FunctionalInterface
    public interface HelloPrint {
        void print();
        // void esheOdna(); сразу нас поймает на ошибке
    }

    public static void main(String[] args) {
        method();
        stringWarnings();

        HelloPrint hello = () -> System.out.println("hello");
    }
}
