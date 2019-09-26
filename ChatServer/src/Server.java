/**
 * @Author Ali Al-Sharefi
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class make sure that our server is running on multiple threads
 */

public class Server {

    static final int MAX = 5;


    public static void main(String[] args) {
        Runnable r1 = new ServerThread("Task1",3300);
        Runnable r2 = new ServerThread("Task2",3301);
        Runnable r3 = new ServerThread("Task3",3302);
        Runnable r4 = new ServerThread("Task4",3303);
        Runnable r5 = new ServerThread("Task5",3304);

        /**
         * Creates a thread pool with the MAX numbers of users
         */

        ExecutorService executorService = Executors.newFixedThreadPool(MAX);


        /**
         * Creates 5 objects with a thread in each one
         */

        executorService.execute(r1);
        executorService.execute(r2);
        executorService.execute(r3);
        executorService.execute(r4);
        executorService.execute(r5);

        /**
         * Shuts down the threadpool
         */

        executorService.shutdown();
    }
}
