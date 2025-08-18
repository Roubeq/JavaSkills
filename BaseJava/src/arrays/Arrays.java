package arrays;

public class Arrays {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        String[] names = new String[3];
        names[0] = "Alice";

        int[][] matrix = {{1, 2}, {3, 4}};

        for (int num : numbers) {
            System.out.print(num + " ");  // 1 2 3
        }
    }
}
