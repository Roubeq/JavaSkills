/* Четвертый способ
*   Создать runnable через лямбда выражение
*/
public class ThreadExample5 {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Current thread is " + threadName);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        // При создании можно указать имя потока
        Thread thread = new Thread(runnable, "The thread");
        thread.start();
        Thread thread2 = new Thread(runnable, "The thread2");
        thread2.start();
        Thread thread3 = new Thread(runnable, "The thread3");
        thread3.start();
    }
}
