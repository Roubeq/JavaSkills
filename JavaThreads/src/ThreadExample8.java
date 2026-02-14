public class ThreadExample8 {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                sleep(1000);
                System.out.println("Running");
            }
         };

        Thread newThread = new Thread(runnable);
        newThread.setDaemon(true);
        newThread.start();
        try {
            newThread.join(); // Этот метод позволяет основному потоку "дождаться" потоки, которые еще работают
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
