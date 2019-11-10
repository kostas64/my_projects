//Κωνσταντίνος Ευκαρπίδης - icsd15051

package exercise.pkg3;

import static java.lang.Integer.parseInt;

class Calc extends Thread{
    //Ιδιότητες κλάσσης
    static int counter=0;
    int start_num;
    int end_num;
    
    //Constructor
    public Calc(int start_num,int end_num)
    {
        this.start_num=start_num;
        this.end_num=end_num;
    }
    
    //Default Constructor
    public Calc()
    {
       
    }
    
    //Ελέγχουμε με ξεχωριστή συνάρτηση αν ο αριθμός είναι πρώτος.Επιστρέφει true αν είναι,αλλιώς false
    public boolean check_prime(int num) {
       
    //Ελεέγχουμε αν ο αριθμός μας είναι άρτιος με τον τελεστή BITWISE AND
    if(num > 2 && (num & 1) == 0)
       return false; 
     
    //Αφού έφτασε εδώ σημαίνει οτι δεν είναι άρτιος,ελέγχουμε αν είναι πολλαπλάσιο του 3
    for(int i = 3; i * i <= num; i += 2){
         if (num % i == 0) 
            return false;
    }     
     
    return true;
}
    
     @Override
    public void run() {
                //Αν η check_prime μας επιστρέψει true τότε θα μπει στην if και θα αυξήσει τον μετρητή κατά 1
		for (int i = start_num; i <= end_num; i++){
                           if(check_prime(i))
                               counter++;                         
                        }	 
	}
}

public class Exercise3 {
 
    public static void main(String[] args) {
       
        //Μέθοδος νημάτων
        //Ξεκινάω από 2 γιατί το 0,1 δεν θεωρούνται πρώτοι αριθμοί
        int start_num=2;
        
        //parseInt(args[0]) -> Επιστρέφει την τιμή που του έχω περάσει ως argument
        int end_num=10000000/parseInt(args[0]); 
        
        //Έναρξη χρονομέτρησης σε milliseconds
        long tStart = System.currentTimeMillis(); 
           
        //Δημιουργεί τόσα αντικείμενα όσα του έχω περάσει ως παράμετρο πριν την έναρξη του προγράμματος
        for(int i=0; i<parseInt(args[0]); i++){
           
           Calc t1 = new Calc(start_num,end_num);
           t1.start();
            try {
			//Περιμένουμε τα threads να τελειώσουν
			t1.join();
	
		} catch (InterruptedException e) {}
           
           //Ενημέρωση παραμέτρων
           start_num=end_num+1;
           end_num = end_num + 10000000/parseInt(args[0]);
        } 
        //Τέλος χρονομέτρησης
        long tEnd = System.currentTimeMillis(); 
        
       //Εμφάνιση αποτελεσμάτων με την μέθοδο των threads
       System.out.println("Οι πρώτοι αριθμοί από το 2-10000000 είναι " + Calc.counter);
       System.out.println("Ο χρόνος με threads είναι " + (tEnd-tStart) + " milliseconds");
         
      tStart = System.currentTimeMillis(); 
    
      //flag1 -> Μεταβλητή που χρησιμοποιώ επειδή τώρα δεν έχω return
      boolean flag1;
      int counter=0;
      for(int i=2; i<100000000; i++){
          flag1=true;
          
          //Χρησιμοποιώ πάλι το ίδιο αλγόριθμο με την συνάρτηση check_prime
    if(i > 2 && (i & 1) == 0)
       flag1 = false; 
    
    for(int y = 3; y * y <= i; y += 2){
         if (i % y == 0) 
             flag1 = false;        
    }   
        //Αύξηση μετρητή
         if(flag1)
            counter++; 
           }
     
    tEnd = System.currentTimeMillis(); 
    
    //Το δοκίμασα μέχρι τα 8μιση λεπτά και δεν μου είχε εμφανίσει αποτέλεσμα ενώ για πιο λίγους αριθμούς δούλευε
    //Άρα προτιμάμε την μέθοδο των threads για καλύτερη διαχείρηση πόρων και εξοικονόμηση χρόνου
    System.out.println("Ο χρόνος χωρίς threads είναι: " + (tEnd-tStart));    
    
    }
}
