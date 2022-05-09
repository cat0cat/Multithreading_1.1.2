import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // Создаем задачу с результатом типа Integer
        Callable<Integer> myCallable1 = new MyCallable("поток 1");
        Callable<Integer> myCallable2 = new MyCallable("поток 2");
        Callable<Integer> myCallable3 = new MyCallable("поток 3");
        Callable<Integer> myCallable4 = new MyCallable("поток 4");
        // Создаем пул потоков фиксированного размера
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        // Отправляем задачу на выполнение в пул потоков
        final Future<Integer> task1 = threadPool.submit(myCallable1);
        final Future<Integer> task2 = threadPool.submit(myCallable2);
        final Future<Integer> task3 = threadPool.submit(myCallable3);
        final Future<Integer> task4 = threadPool.submit(myCallable4);
        // Получаем результат
        try {
            System.out.println(myCallable1 + " выполнил задачу " + task1.get() + " раз");
            System.out.println(myCallable2 + " выполнил задачу " + task2.get() + " раз");
            System.out.println(myCallable3 + " выполнил задачу " + task3.get() + " раз");
            System.out.println(myCallable4 + " выполнил задачу " + task4.get() + " раз");
            List<Future<Integer>> resultList = threadPool.invokeAll(Arrays.asList(myCallable1, myCallable2, myCallable3, myCallable4));
            for (Future<Integer> integerFuture : resultList) {
                System.out.println("Результат: " + integerFuture.get());
            }
            Integer result = threadPool.invokeAny(Arrays.asList(myCallable1, myCallable2, myCallable3, myCallable4));
            System.out.println("Результат первой успешно выполненной задачи: " + result);
        } catch(ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }

        // Завершаем работу пула потоков
        System.out.println("Завершаю все потоки.");
        threadPool.shutdown();
    }


}