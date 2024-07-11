import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTask {
    private static final int MAX_NUMBER = 100;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        AtomicInteger counter = new AtomicInteger(1);

        for (int i = 0; i < MAX_NUMBER; i++) {
            int num = i + 1;
            threadPool.execute(() -> {
                synchronized (counter) {
                    while (counter.get() != num) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Sayı: " + num );
                    counter.incrementAndGet();
                    counter.notifyAll();
                }
            });
        }

        threadPool.shutdown();
    }
}