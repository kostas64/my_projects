
package hlektr_krypto;



public class Kokkino extends Grammata{
    
    public Kokkino(char val,int point) //Constructor
    {
        super(val,point);
    }
    
    public Kokkino() //Default Constructor
    {
        
    }
    
        @Override
    public String toString()
    {
        return ("\nval " + getVal() + " point " + getPoint() + " color " + getColor());
    }
    
     @Override
    public int getPoint(){
        return point;
    }
}
