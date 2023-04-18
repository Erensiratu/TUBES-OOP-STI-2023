import java.util.ArrayList;
import java.util.List;

public class Timer {
    private int count;
    private List<Worker> workerList;

    public Timer() {
        count = 0;
        workerList = new ArrayList<>();
    }

    public synchronized void start() {
        Thread timerThread = new Thread(() -> {
            while (true) {
                int activeWorkers = 0;
                for (Worker worker : workerList) {
                    if (worker.isActive()) {
                        activeWorkers++;
                    }
                }

                if (activeWorkers > 0) {
                    // At least one worker is active, so increment the count
                    count++;
                    System.out.println("Count is now: " + count);
                } else {
                    // No active workers, so suspend the thread and wait for a worker to be added
                    try {
                        System.out.println("No active workers, suspending timer thread...");
                        synchronized(this) {
                            wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        timerThread.start();
    }

    public synchronized void addWorker(Worker worker) {
        workerList.add(worker);
        worker.start();

        // Notify the timer thread that a new worker has been added
        System.out.println("Worker added, resuming timer thread...");
        synchronized(this) {
            notify();
        }
    }
}