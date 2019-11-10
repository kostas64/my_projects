package producerconsumer;

/**
 *
 * @author Pipis
 */
public class ProducerConsumer {

    
    public static void main(String[] args) throws InterruptedException {
        Product prod = new Product();
        Producer p1 = new Producer(prod, "Chef");
        Producer p2 = new Producer(prod, "Boufetzis");
        Consumer c1 = new Consumer(prod, "waiter_A"); //Α - ροφηματα , Β - φαγητα
        Consumer c2 = new Consumer(prod, "waiter_B");
        p1.start();
        p2.start();
        c1.start();
        c2.start();
//        p1.join();
//        p2.join();
//        c1.join();
//        c2.join();
//        Thread.currentThread().join();
        
    }

}
