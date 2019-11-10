package producerconsumer;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pipis
 */
public class Producer extends Thread {

    private Product prod;
    private String competence; //Chef ή Boufetzis

    Producer(Product prod, String competence) {
        this.prod = prod;
        this.competence = competence;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 40; i++) {
            Random rand = new Random();
            try {
                //1 - καφες , 2- χυμος , 3 - μακαρονια , 4 - τοστ (1,2 α σερβιτόρος & 3,4 β σερβιτόρος)
                prod.add(rand.nextInt(4) + 1,this); 
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                  //Χρόνος εκτέλεσης διαδικασίας (παραγωγή προιόντος ή σερβίρισμα)
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Συνάρτηση αποστολής ιδιότητας παραγωγού (chef ή boufetzis)
    public String getCompetence() {
        return competence;
    }
    
}
