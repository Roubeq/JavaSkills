public class ThreadExample7 {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            while (true) {
                sleep(500);
                System.out.println("Running");
            }
        };
        // Программа не завершится, пока есть работающий поток
        Thread newThread = new Thread(runnable);
        newThread.setDaemon(true); // После этого поток, завершится вместе с основным
        newThread.start();
        sleep(3200);

    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
