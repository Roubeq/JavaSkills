package variables;

public class Variables {
    public static void main(String[] args) {
        // primitive
        byte byteVar = 127;
        short shortVar = 32767;
        int intVar = 2_147_483_647;
        long longVar = 9_223_372_036_854_775_807L;
        float floatVar = 3.14f;
        double doubleVar = 3.1415926535;
        char charVar = 'A';
        boolean boolVar = true;

        // reference
        String stringVar = "Hello, Java!";
        Object objectVar = new Object();

        System.out.println("intVar: " + intVar);
    }
}