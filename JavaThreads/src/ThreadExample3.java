
/* Второй способ
*   Создать класс, реализующий интерфейс Runnable
*/
public class ThreadExample3 {

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread started");
            System.out.println("Thread finished");
        }
    }

    public static void main(String[] args){
        Thread myThread = new Thread(new MyRunnable());
        myThread.start();
    }
}
