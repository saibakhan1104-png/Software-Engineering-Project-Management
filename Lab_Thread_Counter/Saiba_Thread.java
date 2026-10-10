import java.util.concurrent.atomic.AtomicLong;

public class Saiba_Thread {

    static AtomicLong safeStatic = new AtomicLong(0); // Experiment A (thread-safe)
    static long unsafeStatic = 0;                     // Experiment B (no synchronization)

    static class CounterTask extends Thread {
        long instanceCount = 0;          // non-static: one copy per object
        long increments;
        boolean threadSafe;

        CounterTask(long increments, boolean threadSafe) {
            this.increments = increments;
            this.threadSafe = threadSafe;
        }

        public void run() {
            for (long i = 0; i < increments; i++) {
                instanceCount++;
                if (threadSafe) safeStatic.incrementAndGet();
                else unsafeStatic++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = Integer.parseInt(args[0]);
        long m = Long.parseLong(args[1]);
        boolean threadSafe = Boolean.parseBoolean(args[2]);

        CounterTask[] tasks = new CounterTask[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = new CounterTask(m, threadSafe);
            tasks[i].start();
        }
        for (CounterTask t : tasks) t.join();   // wait before reading totals

        long nonStatic = 0;
        for (CounterTask t : tasks) nonStatic += t.instanceCount;
        long staticCount = threadSafe ? safeStatic.get() : unsafeStatic;

        long diff = Math.abs(staticCount - nonStatic);
        double pct = (nonStatic == 0) ? (staticCount == 0 ? 0.0 : Double.NaN)
                : diff * 100.0 / nonStatic;

        System.out.println("Threads: " + n + ", Increments/thread: " + m
                + ", Thread-safe: " + threadSafe);
        System.out.println("Expected: " + (long) n * m);
        System.out.println("Static count: " + staticCount);
        System.out.println("Non-static total: " + nonStatic);
        System.out.println("Absolute difference: " + diff);
        System.out.printf("Percentage difference: %.4f%%%n", pct);
    }
}
