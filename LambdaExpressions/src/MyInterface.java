public interface MyInterface {
    void print(String s);

    static void function() {
        System.out.println("А это другая функция, недоступная из лямбды, но она может быть определена");
    }
}
