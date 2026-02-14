// Останавливаемый поток, stop() - deprecated
public class ThreadExample6 {

    public static class StoppableThread implements Runnable {

        private boolean stopRequested = false;

        public synchronized void requestStop() {
            this.stopRequested = true;
        }

        public synchronized boolean isStopRequested() {
            return this.stopRequested;
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            System.out.println("StoppableThread is running");
            while (!isStopRequested()) {
                sleep(1000);
                System.out.println("...");
            }
            System.out.println("StoppableThread is stopped");
        }
    }

    public static void main(String[] args) {
        StoppableThread stoppableThread = new StoppableThread();
        Thread thread = new Thread(stoppableThread, "The Thread");
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("requesting stop");
        stoppableThread.requestStop();
        System.out.println("stop requested");
    }
}
