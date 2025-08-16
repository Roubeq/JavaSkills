package operators;

public class Operators {
    public static void main(String[] args) {
        int a = 10, b = 20;

        // arithmetic
        System.out.println(a + b);  // 30
        System.out.println(a % 3);  // 1 (остаток)

        // equals
        System.out.println(a == b); // false
        System.out.println(a < b);  // true

        // logic
        boolean x = true, y = false;
        System.out.println(x && y); // false
        System.out.println(!x);      // false

        // bitwise
        System.out.println(a & b);   // 0
        System.out.println(a << 2);  // 40 (сдвиг влево)
    }
}
