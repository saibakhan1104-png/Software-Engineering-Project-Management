class MyThread extends Thread {

    MyThread(String name) {
        super(name);
    }
    // run()
    // Contains the task performed by the thread
    @Override
    public void run() {

        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + " is running: " + i);

            try {
                // sleep()
                // Pauses the thread
                Thread.sleep(500);

            } catch (InterruptedException e) {

                System.out.println("Thread interrupted");
                return;
            }
        }
    }
}


public class ThreadMethods {

    public static void main(String[] args)
            throws InterruptedException {

        // currentThread()
        // Returns the currently running thread
        Thread mainThread = Thread.currentThread();

        System.out.println("Current Thread: " + mainThread.getName());


        // Create a thread
        MyThread t1 = new MyThread("Worker-1");


        // getName()
        // Returns the name of the thread
        System.out.println("Thread Name: " + t1.getName());


        // setName()
        // Changes the name of the thread
        t1.setName("My-Worker");

        System.out.println( "New Thread Name: " + t1.getName());


        // getId()
        // Returns the unique ID of the thread
        System.out.println("Thread ID: " + t1.getId());


        // getPriority()
        // Returns the priority of the thread
        System.out.println("Thread Priority: " + t1.getPriority());


        // setPriority()
        // Changes the priority of the thread
        t1.setPriority(Thread.MAX_PRIORITY);

        System.out.println("New Priority: " + t1.getPriority());


        // getState()
        // Returns the current state of the thread
        System.out.println("Thread State: " + t1.getState());


        // isAlive()
        // Checks whether the thread is alive
        System.out.println("Is Alive before start: " + t1.isAlive());


        // start()
        // Starts the thread and automatically calls run()
        t1.start();

        System.out.println("Is Alive after start: " + t1.isAlive());


        // getThreadGroup()
        // Returns the thread group of the thread
        // Checked before the thread finishes
        System.out.println("Thread Group: " + t1.getThreadGroup().getName());


        // join()
        // Main thread waits until t1 finishes
        t1.join();

        System.out.println("Thread finished.");


        // yield()
        // Gives other threads a chance to execute
        Thread.yield();

        System.out.println("yield() executed");


        // Create another thread
        MyThread t2 = new MyThread("Worker-2");

        t2.start();

        Thread.sleep(500);


        // interrupt()
        // Interrupts the thread
        t2.interrupt();

        System.out.println("Worker-2 interrupted");


        // isInterrupted()
        // Checks whether the thread has been interrupted
        System.out.println("Is Interrupted: " + t2.isInterrupted());

        // Wait for t2 to finish
        t2.join();

        // Create a daemon thread
        MyThread t3 = new MyThread("Daemon-Thread");

        // setDaemon()
        // Makes the thread a daemon thread
        // Must be called before start()
        t3.setDaemon(true);

        // isDaemon()
        // Checks whether the thread is a daemon thread
        System.out.println("Is Daemon: " + t3.isDaemon());

        // Start daemon thread
        t3.start();

        // holdsLock()
        // Checks whether the current thread owns
        // the lock of an object
        Object lock = new Object();

        synchronized (lock) {

            System.out.println("Holds Lock: " + Thread.holdsLock(lock));
        }


        // Program finished
        System.out.println("Program Finished.");
    }
}