import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchFinally {

    public static void main(String[] args) {
        demoBasicTryCatch();
        demoMultipleCatchBlocks();
        demoFinallyBlock();
        demoTryWithResources();
        demoNestedTryCatch();
        demoCustomException();
        demoFinallyWithReturn();
        demoExceptionPropagation();
    }

    private static void demoBasicTryCatch() {
        System.out.println("1. Базовый трайкетч");
        try {
            int result = 10 / 0; // ArithmeticException
            System.out.println("Результат: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: Деление на ноль!");
            System.out.println("Сообщение: " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoMultipleCatchBlocks() {
        System.out.println("2. Несколько catch-блоков:");

        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]); // ArrayIndexOutOfBoundsException

            String nullStr = null;
            System.out.println(nullStr.length()); // NullPointerException
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Выход за границы массива!");
        }
        catch (NullPointerException e) {
            System.out.println("Ошибка: Работа с null объектом!");
        }
        catch (Exception e) {
            System.out.println("Общая ошибка: " + e.getMessage()); // Other exceptions
        }
        System.out.println();
    }

    private static void demoFinallyBlock() {
        System.out.println("3. Блок finally:");

        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.print("Введите число: ");
            int num = scanner.nextInt();
            System.out.println("Вы ввели: " + num);
        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка: Неверный формат числа!");
        }
        finally {
            if (scanner != null) {
                scanner.close();
                System.out.println("Scanner закрыт в finally блоке");
            }
        }
        System.out.println();
    }

    private static void demoTryWithResources() {
        System.out.println("4. Try-with-resources:");

        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/resources/config.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Прочитано: " + line);
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Ошибка: Файл не найден!");
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
        // Автоматически вызывается reader.close()
        System.out.println("Ресурсы автоматически закрыты");
        System.out.println();
    }

    private static void demoNestedTryCatch() {
        System.out.println("5. Вложенные try-catch блоки:");

        try {
            try {
                int[] arr = new int[-5]; // NegativeArraySizeException
            }
            catch (NegativeArraySizeException e) {
                System.out.println("Внутренний catch: Неверный размер массива");
                throw new IllegalArgumentException("Преобразованная ошибка");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Внешний catch: " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoCustomException() {
        System.out.println("6. Пользовательские исключения:");

        try {
            validateAge(15);
        }
        catch (CustomException e) {
            System.out.println("Поймано пользовательское исключение: " + e.getMessage());
            System.out.println("Код ошибки: " + e.getErrorCode());
        }
        System.out.println();
    }

    private static void validateAge(int age) throws CustomException {
        if (age < 18) {
            throw new CustomException("Возраст должен быть ≥ 18", 1001);
        }
    }

    private static void demoFinallyWithReturn() {
        System.out.println("7. Finally с return:");

        System.out.println("Результат метода: " + testFinallyReturn());
        System.out.println();
    }

    private static String testFinallyReturn() {
        try {
            System.out.println("В try блоке");
            return "Возврат из try";
        }
        finally {
            System.out.println("Finally блок выполняется ДО return!");
        }
    }

    private static void demoExceptionPropagation() {
        System.out.println("8. Проброс исключений:");

        try {
            methodA();
        }
        catch (Exception e) {
            System.out.println("Поймано в main: " + e.getClass().getSimpleName());
        }
        System.out.println();
    }

    private static void methodA() {
        methodB();
    }

    private static void methodB() {
        throw new RuntimeException("Исключение из methodB");
    }
}