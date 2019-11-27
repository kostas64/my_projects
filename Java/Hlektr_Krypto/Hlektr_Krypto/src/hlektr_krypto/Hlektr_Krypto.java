
 
package hlektr_krypto;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;


public class Hlektr_Krypto {

   
    public static void main(String[] args) throws Exception {
       
    
    Leitourgies l1 = new Leitourgies();
    EventQueue.invokeLater(() -> {
            GFrame ex = new GFrame(l1);
            //ex.getContentPane().setBackground(Color.BLUE.brighter());
            
            ex.setVisible(true);
    });
    
    
    
    
    
   
    
   // int a=l1.Gemisma_tablo(1,pontoi_anagrama,isostixia);
   // Grammata tablo[][]=new Grammata[(int)Math.sqrt(a)][(int)Math.sqrt(a)];  
   //tablo=l1.display(a);
     // l1.swap(0, 0, 4, 4);
    //l1.balader(tablo[3][3],isostixia,pontoi_anagrama);
    //l1.delete_line(a,2,isostixia,pontoi_anagrama);
   // l1.display(a);
  // ArrayList<Grammata> abc = new ArrayList<>();
   
   //abc.add(tablo[0][0]);
 //  abc.add(tablo[1][1]);
 //  abc.add(tablo[2][2]);
 //  abc.add(tablo[3][3]); 
 //  pontoi=l1.Ypologismos(abc);
//   System.out.println(pontoi);
    }
        
}
