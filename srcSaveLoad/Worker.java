

public class Worker {
    private boolean active;

    public Worker() {
        active = false;
    }

    public synchronized void start() {
        Thread workerThread = new Thread(() -> {
            active = true;
            System.out.println("Worker thread started");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            active = false;
            System.out.println("Worker thread finished");
        });

        workerThread.start();
    }

    public synchronized boolean isActive() {
        return active;
    }
}