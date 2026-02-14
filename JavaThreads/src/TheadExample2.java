/* Первый способ
*   Создать класс, расширяющий класс Thread и реализовать(переопределить) функцию run()
*/
public class TheadExample2 {

    public static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("Thread started");
            System.out.println("Thread finished");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
