/* Третий способ
    Создать анонимный класс, реализующий интерфейс Runnable
*/
public class ThreadExample4 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread started");
                System.out.println("Thread finished");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
