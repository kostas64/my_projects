//icsd10011- Αντωνιάδης Χαράλαμπος
//icsd15051- Ευκαρπίδης Κωνσταντίνος
 
package hlektr_krypto;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;


public class Hlektr_Krypto {

   
    public static void main(String[] args) throws Exception {
       
    
    Leitourgies l1 = new Leitourgies();
    EventQueue.invokeLater(() -> {  //Κλήση γραφικών
            GFrame ex = new GFrame(l1); 
            ex.setVisible(true);      
            
    });

    }      
}
