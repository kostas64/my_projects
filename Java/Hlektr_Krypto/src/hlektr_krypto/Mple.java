
package hlektr_krypto;




public class Mple extends Grammata{
    
    public Mple(char val,int point) //Constructor
    {
        super(val,point);
    }
    
    public Mple() //Default Constructor
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
