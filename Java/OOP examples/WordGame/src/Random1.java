import java.util.Random;


public class Random1 extends Random{ //Κανω override την Random ωστε να μπορει να επιστεψει και 0 γιατι ανναφερεται σε index πινακα οπυ το θελουμε το 0ικο στοιχειο
    @Override
    public int nextInt(int bound) {
   if (bound <= 0) //Του χω βαλει να επιστρεψει 0 και οχι exception οπως ειχε πριν
      return 0;   
   if ((bound & -bound) == bound)  
     return (int)((bound * (long)next(31)) >> 31);

   int bits, val;
   do {
       bits = (int) next(31);
       val = bits % bound;
   } while (bits - val + (bound-1) < 0);
   return val;
 }
    
   public Random1(long seed) //Κανω super στον constructon της Random1 ωστε να παρει τα ορισματα του constructor της Random.
    {
        super();
    }
}
