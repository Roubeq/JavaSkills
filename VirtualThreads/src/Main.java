public class Main {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("print " + i);
            }
        };

        Thread thread = Thread.ofVirtual().start(runnable);

        // этот не стартанет сразу
        Thread threadSecond = Thread.ofVirtual().unstarted(runnable);
        threadSecond.start();


        // основной поток приостановится, пока не выполнится виртуальный
        try {
            threadSecond.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}