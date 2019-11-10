package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pipis
 */
public class Product {

    private int[] products = new int[11]; //Πάγκος
    private int front = -1, rear = -1; //Δείκτες κυκλικής λίστας

    public void display() {
        //Εμφάνιση πάγκου
        int temp = front;
        System.out.print("Πάγκος: [ ");     
        if (temp == rear) {
            System.out.print("empty");
        } else {
            while (temp != rear) {
                temp = (temp + 1) % products.length;
                System.out.print(products[temp] + " ");
            }
        }
        System.out.println("]");
    }

    public synchronized void remove(Consumer c1) throws InterruptedException {
        int content;
        int front_p = (front + 1) % 11;
        //Έλεγχος διαθεσιμότητας προιόντων στον παγκο
        while (isEmpty()) {
            try {
                //Στο τελος του προγράμματος εμφανίζεται 2 φορές το παρακάτω μύνημα
                //γιατί 2 είναι και τα Thread που καλούν την συνάρτηση και τελειώνουν (2 σερβιτόροι)
                System.out.println("The table is empty");
                wait();
            } catch (InterruptedException e) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        //Έλεγχος εγκυρότητας καταναλωτή-προιόν
        if ((Thread.currentThread().getName().equals("Thread-3") && (products[front_p] == 3 || products[front_p] == 4))
                || ((Thread.currentThread().getName().equals("Thread-2") && (products[front_p] == 1 || products[front_p] == 2)))) {

            content = dequeue();

            if (content != 0) {
                System.out.println(c1.getName1() + " took " + content + " from the table!");
            }
            //Εφόσον αφαίρεσε προιόν
            notifyAll();
        } else {
            System.out.println(c1.getName1() + " didnt find something");
        }
        
    }

    public synchronized void add(int prod_id, Producer p1) throws InterruptedException {

        //Έλεγχος διαθεσιμων προιόντων στον πάγκο
        while (isFull()) {
            try {
                System.out.println("The table is full,you can't add nothing at this moment...");
                //Περιμένει μέχρι να ελευθερωθεί κάποια θέση στο πάγκο
                wait();
            } catch (InterruptedException e) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        //Κλήση συνάρτησης προσθήκης προιόντος
        enqueue(prod_id);

        System.out.println(p1.getCompetence() + " added " + prod_id + " to the table!");
        //Εμφάνιση λίστας και ενημέρωση όλων των Thread
        display();
        notifyAll();

    }

    public boolean isFull() {
        //Χρήση modulo για υλοποίηση κυκλικότητας
        return (((rear + 1) % 11 == front) ? true : false);
    }

    public boolean isEmpty() {
        return (front == rear);
    }

    public int dequeue() {
        //Συνάρτηση αφαίρεσης από πάγκο
        front = (front + 1) % 11;
        return (products[front]);
    }

    public void enqueue(int x) {
        //Συνάρτηση προσθήκης στο πάγκο
        rear = (rear + 1) % 11;
        products[rear] = x;
    }

}
