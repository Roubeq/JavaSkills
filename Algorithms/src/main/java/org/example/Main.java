package org.example;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Main {

    //Map<Integer, BigInteger> cache = new HashMap<>();

    int counter;

    private BigInteger fibonacci(int n) {
        counter++;
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        BigInteger previous = BigInteger.valueOf(0);
        BigInteger current = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            BigInteger newCurrent = current.add(previous);
            previous = current;
            current = newCurrent;
        }
        return current;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private void run() {
        System.out.println(fibonacci(100000));
        System.out.println(counter + " calls");
    }
    public static void main(String[] args) {
        long startMillisTime = System.currentTimeMillis();
        new Main().run();
        long finishMillisTime = System.currentTimeMillis();
        System.out.println(finishMillisTime - startMillisTime + " ms");
    }
}