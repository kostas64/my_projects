
package hlektr_krypto;

public class player { 
    private String name="Guest";

    private int pontoi=0;
    private final int pontoi_nikis=100;
    private final int lexeis_nikis=6;
    
    public player() //Constructor
    {

    }
    public void addPontoi(int pontoi) //Ποντοι που εχει συλλεξει ο χρηστης
    {
        this.pontoi+=pontoi;
    }
    
    public int getPontoi() //Getters
    {
        return pontoi;
    }
    
    public int getPontoi_nikis()
    {
        return pontoi_nikis;
    }
    
    public int getLexeis_nikis()
    {
        return lexeis_nikis;
    }
    
    public String getName()
    {
        return name;
    }
    public void setName(String str){
        this.name=str;
    }
}
