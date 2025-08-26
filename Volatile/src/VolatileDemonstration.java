/**
 * Демонстрация ПОЛНОЙ гарантии видимости через volatile
 * Если поток A пишет в volatile, а поток B читает тот же volatile,
 * то все переменные, видимые потоку A ДО записи, становятся видимыми потоку B ПОСЛЕ чтения
 */
public class VolatileDemonstration {
    private static int x = 0;
    private static int y = 0;
    private static String message = "Изначальное сообщение";

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Демонстрация полной гарантии видимости ===");
        System.out.println("Начальные значения: x=" + x + ", y=" + y + ", message='" + message + "'");

        Thread writerThread = new Thread(() -> {
            x = 42;
            y = 100;
            message = "Новое сообщение от писателя";

            System.out.println("Писатель: установил x=" + x + ", y=" + y + ", message='" + message + "'");

            // КРИТИЧЕСКИЙ МОМЕНТ: запись в volatile переменную
            flag = true; // Эта запись "публикует" все предыдущие изменения! если менять порядок, то может быть эррор

            System.out.println("Писатель: установил flag = " + flag);
        });

        Thread readerThread = new Thread(() -> {
            int iterations = 0;
            while (!flag) {
                iterations++;
            }

            // После чтения flag=true, мы гарантированно видим ВСЕ изменения,
            // сделанные писателем ДО записи в flag!
            System.out.println("Читатель: увидел x=" + x + ", y=" + y + ", message='" + message + "'");
            System.out.println("Читатель: потребовалось " + iterations + " итераций ожидания");

            System.out.println("Читатель: повторное чтение - x=" + x + ", y=" + y);
        });

        readerThread.start();
        Thread.sleep(100); // Небольшая задержка для запуска в правильном порядке
        writerThread.start();

        readerThread.join();
        writerThread.join();

        System.out.println("=== Программа завершена ===");
        System.out.println("Гарантия: читатель УВИДЕЛ ВСЕ изменения писателя!");
    }
}