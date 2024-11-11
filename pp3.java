import java.util.concurrent.Semaphore;

class ProducerConsumer {
    private int item = 0;   // Shared item between producer and consumer
    private Semaphore empty = new Semaphore(1); // Initially empty
    private Semaphore full = new Semaphore(0);  // Initially not full

    public void produce() throws InterruptedException {
        empty.acquire();                   // Wait if not empty
        item++;                            // Produce an item
        System.out.println("Produced: " + item);
        full.release();                    // Signal full
    }

    public void consume() throws InterruptedException {
        full.acquire();                    // Wait if not full
        System.out.println("Consumed: " + item);
        empty.release();                   // Signal empty
    }
}

public class pp3 {
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        // Producer thread
        new Thread(() -> {
            try {
                while (true) {
                    pc.produce();
                    Thread.sleep(500);    
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                while (true) {
                    pc.consume();
                    Thread.sleep(1000);    
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}