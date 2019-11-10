
package producerconsumer;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pipis
 */
public class Consumer extends Thread {

    private Product prod;
    private String name1;
    private boolean b1,b2=false;

    Consumer(Product prod, String name) {
        this.name1 = name;
        this.prod = prod;
    }

    @Override
    public void run() {
        int y = 0;
        //Η επανάληψη τρέχει πιο πολλές φορές από τον παραγωγό ώστε να τελειώνουν οι καταναλωτές τελευταίοι 
        //και να αδειάζει ο πάγκος
        for (int i = 0; i < 100; i++) {
            try {
                 prod.remove(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //Χρόνος εκτέλεσης διαδικασίας (παραγωγή προιόντος ή σερβίρισμα)
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    //Συνάρτηση αποστολής ονόματος σερβιτόρου (Α ή Β)
    public String getName1() {
        return name1;
    }
}
