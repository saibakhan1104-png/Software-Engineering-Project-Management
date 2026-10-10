class StaticThread extends Thread {

    // One shared counter for all threads
    static long counter = 0;

    // Second largest integer in Java
    static final int SECOND_LARGEST = Integer.MAX_VALUE - 1;

    @Override
    public void run() {

        while (Main.running) {

            for (int i = 0; i < 100; i++) {

                int number = SECOND_LARGEST;

                if (number == Integer.MAX_VALUE - 1) {
                    counter++;
                }
            }
        }
    }
}


class NonStaticThread extends Thread {

    // Each thread has its own counter
    long counter = 0;

    // Second largest integer in Java
    static final int SECOND_LARGEST = Integer.MAX_VALUE - 1;

    @Override
    public void run() {

        while (Main.running) {

            for (int i = 0; i < 100; i++) {

                int number = SECOND_LARGEST;

                if (number == Integer.MAX_VALUE - 1) {
                    counter++;
                }
            }
        }
    }
}


public class Main {

    // Number of threads
    static final int NUMBER_OF_THREADS = 100;

    // Loop count
    static final int LOOP_COUNT = 100;

    // Test duration
    // Change this to 1, 5 or 15
    static final int TEST_MINUTES = 1;

    // Controls the threads
    static volatile boolean running = true;


    public static void main(String[] args) throws InterruptedException {

        // ==================================================
        // STATIC THREAD TEST
        // ==================================================

        System.out.println("======================================");
        System.out.println("       STATIC THREAD TEST");
        System.out.println("======================================");

        StaticThread.counter = 0;
        running = true;

        StaticThread[] staticThreads = new StaticThread[NUMBER_OF_THREADS];

        long startTime = System.currentTimeMillis();

        // Create and start 100 threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {

            staticThreads[i] = new StaticThread();

            staticThreads[i].start();
        }

        // Run for selected time
        Thread.sleep(TEST_MINUTES * 60L * 1000L);

        // Tell all threads to stop
        running = false;

        // Wait for all threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {

            staticThreads[i].join();
        }

        long endTime = System.currentTimeMillis();

        long staticTime = endTime - startTime;

        System.out.println("Number of Threads : "
                + NUMBER_OF_THREADS);

        System.out.println("Loop Count        : "
                + LOOP_COUNT);

        System.out.println("Test Duration     : "
                + TEST_MINUTES + " minute(s)");

        System.out.println("Static Counter    : "
                + StaticThread.counter);

        System.out.println("Execution Time    : "
                + staticTime + " ms");


        // ==================================================
        // NON-STATIC THREAD TEST
        // ==================================================

        System.out.println();
        System.out.println("======================================");
        System.out.println("      NON-STATIC THREAD TEST");
        System.out.println("======================================");

        running = true;

        NonStaticThread[] nonStaticThreads =
                new NonStaticThread[NUMBER_OF_THREADS];

        startTime = System.currentTimeMillis();

        // Create and start 100 threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {

            nonStaticThreads[i] = new NonStaticThread();

            nonStaticThreads[i].start();
        }

        // Run for selected time
        Thread.sleep(TEST_MINUTES * 60L * 1000L);

        // Tell all threads to stop
        running = false;

        // Wait for all threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {

            nonStaticThreads[i].join();
        }

        endTime = System.currentTimeMillis();

        long nonStaticTime = endTime - startTime;


        // Add all individual counters
        long totalNonStaticCounter = 0;

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {

            totalNonStaticCounter =
                    totalNonStaticCounter
                            + nonStaticThreads[i].counter;
        }


        System.out.println("Number of Threads : " + NUMBER_OF_THREADS);

        System.out.println("Loop Count        : " + LOOP_COUNT);

        System.out.println("Test Duration     : " + TEST_MINUTES + " minute(s)");

        System.out.println("Non-Static Counter: " + totalNonStaticCounter);

        System.out.println("Execution Time    : " + nonStaticTime + " ms");

        System.out.println();
        System.out.println("======================================");
        System.out.println("           COMPARISON");
        System.out.println("======================================");

        System.out.println("Static Count      : " + StaticThread.counter);

        System.out.println("Non-Static Count  : " + totalNonStaticCounter);

        System.out.println("Static Time       : " + staticTime + " ms");

        System.out.println("Non-Static Time   : " + nonStaticTime + " ms");

        System.out.println("Count Difference   : " + Math.abs(StaticThread.counter - totalNonStaticCounter));

        System.out.println("Time Difference    : " + Math.abs(staticTime - nonStaticTime) + " ms");

        System.out.println("======================================");
    }
}