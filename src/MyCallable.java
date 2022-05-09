import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer>{
    private String name;
    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        int count = 1;
        try {
            while(count <= 4) {
                Thread.sleep(2500);
                System.out.println("Я " + Thread.currentThread().getName() + ". Всем привет!");
                count++;
            }
        } catch (InterruptedException err) {

        }
        return count;
    }

    @Override
    public String toString() {
        return name;
    }
}
