import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class ThreadTask {

    public static void main(String[] args) {
        int threadCount = 10;
        ExecutorService sabitHavuz = Executors.newFixedThreadPool(threadCount);
        CyclicBarrier barrier = new CyclicBarrier(threadCount, new Runnable() {
            int count = 1;

            @Override
            public void run() {
                if (count <= 100) {
                    System.out.println("Sayı: " + count);
                    count++;
                }
            }
        });

        for (int i = 0; i < threadCount; i++) {
            sabitHavuz.execute(() -> {
                while (true) {
                    try {
                        Thread.sleep(100);
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            });
        }

        sabitHavuz.shutdown();
    }
}