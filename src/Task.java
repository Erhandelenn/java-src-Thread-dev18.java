class Task implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 100; i++) {
                System.out.println(i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.err.println("Görev kesildi: " + e.getMessage());
        }
    }
}