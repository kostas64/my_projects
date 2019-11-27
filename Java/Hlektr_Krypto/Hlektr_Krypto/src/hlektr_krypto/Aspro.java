
package hlektr_krypto;


public class Aspro extends Grammata{
    
    
    public Aspro(char val,int point) //Constructor
    {
        super(val,point);
    }
    
    public Aspro() //Default Constructor
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
