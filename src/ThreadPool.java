import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPool{

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            Runnable task = new Task();
            executor.execute(task);
        }

        executor.shutdown();
    }
}
