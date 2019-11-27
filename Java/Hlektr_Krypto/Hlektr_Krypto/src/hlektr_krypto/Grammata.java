
package hlektr_krypto;

import java.util.ArrayList;


public abstract class Grammata {
    private char val;
    protected int point;
    private String color="white";
    
    
    public Grammata(char val,int point) //Constructor
    {
        this.val=val;
        this.point=point;
    }
    
    public Grammata() //Default Constructor
    {
        
    }
    
    public void setVal(char val) //seters & geters
    {
          this.val=val;
    }
    
    public void setPoint(int point)
    {
          this.point=point;
    }
       
    public char getVal()
    {
        return val;
    }
    
    public abstract int getPoint();
    
    public void setColor(String color)
    {
        this.color=color;
    }
    
    public String getColor()
    {
        return color;
    } 
}
