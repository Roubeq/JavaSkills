import java.util.ArrayList;
import java.util.List;


// Демонстрация 100_000 виртуальных потоков
public class ArrayVT {
    public static void main(String[] args) {
        List<Thread> vThreads = new ArrayList<>();

        for (int i = 0; i< 100_000; i++) {
            int vIndex = i;
            Thread vThread = Thread.ofVirtual().start(() -> {
                for(int j = 0; j < 10; j ++) {
                    int result = 1;
                    result *= (j+1);
                    System.out.println("Virtual index - " + vIndex +" result - " +result);
                }
            });

            vThreads.add(vThread);
        }

        for (Thread vThread : vThreads) {
            try {
                vThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
