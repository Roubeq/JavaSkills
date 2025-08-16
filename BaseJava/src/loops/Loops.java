package loops;

public class Loops {
    public static void main(String[] args) {
        // for
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");  // 0 1 2 3 4
        }

        // while
        int j = 0;
        while (j < 5) {
            System.out.print(j++ + " ");  // 0 1 2 3 4
        }

        // do-while
        int k = 0;
        do {
            System.out.print(k++ + " ");  // 0 1 2 3 4
        } while (k < 5);

        // for-each
        int[] nums = {1, 2, 3};
        for (int num : nums) {
            if (num == 1) {
                continue; // or break
            }
            System.out.print(num + " ");  // 1 2 3
        }
    }
}
