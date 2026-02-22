package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    class Item {
        int cost;
        int weight;

        public Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }
    }

    private void run() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int w = scanner.nextInt();

        Item items[] = new Item[n];

        for(int i = 0; i < n; i++) {
            int costInput = scanner.nextInt();
            int weightInput = scanner.nextInt();
            items[i] = new Item(costInput,weightInput);
        }

        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double r1 = (double)o1.cost / o1.weight;
                double r2 = (double)o2.cost / o2.weight;

                return -Double.compare(r1,r2);
            }
        });

        double result = 0;

        for (Item item: items) {
            if (item.weight <= w) {
                result += item.cost;
                w -= item.weight;
            } else {
                result += (double) item.cost * w / item.weight;
            }
        }

        System.out.println(result);
    }
    public static void main(String[] args) throws FileNotFoundException {
        new Main().run();
    }
}