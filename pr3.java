import java.util.concurrent.Semaphore;

public class pr3 {
    static Semaphore mutex = new Semaphore(1);       
    static Semaphore database = new Semaphore(1);    
    static int Read_Count = 0;

    static void Reader() throws InterruptedException {
        while (true) {
            mutex.acquire(); // wait(mutex)
            Read_Count++;
            if (Read_Count == 1) {
                database.acquire(); // First reader locks the database for reading
            }
            mutex.release(); // signal(mutex)

            System.out.println("Reader " + Read_Count + " is reading the data...");
            Thread.sleep(1000); // Simulate reading time

            mutex.acquire(); // wait(mutex)
            Read_Count--;
            if (Read_Count == 0) {
                database.release(); // Last reader releases the database
            }
            mutex.release(); // signal(mutex)

            System.out.println("Reader " + (Read_Count + 1) + " finished reading.");
            break;
        }
    }

    static void Writer() throws InterruptedException {
        while (true) {
            database.acquire(); // wait(database)
            System.out.println("Writer is writing to the database...");
            Thread.sleep(1000); // Simulate writing time
            System.out.println("Writer finished writing.");
            database.release(); // signal(database)
            break;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread writer1 = new Thread(() -> {
            try {
                Writer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread writer2 = new Thread(() -> {
            try {
                Writer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread reader1 = new Thread(() -> {
            try {
                Reader();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start the threads
        writer1.start();
        writer2.start();
        reader1.start();

        // Wait for threads to finish
        writer1.join();
        writer2.join();
        reader1.join();
    }
}