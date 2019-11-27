
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
    public int getPoint(){
        return 2*point;
    }
}
