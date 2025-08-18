package strings;

public class Strings {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = new String("World");

        System.out.println(str1.length());      // 5
        System.out.println(str1.concat(str2));   // HelloWorld
        System.out.println(str1.equals("hello")); // false (register)
        System.out.println(str1.substring(1, 3)); // "el"
    }
}
