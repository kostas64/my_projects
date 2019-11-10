
public class Car {
    private String ComName;
    private String Model;
    private String Color;
    private int HP;
    private int Cc;
    private int Code;
    private float Price;
    
     public Car(int a)
        {
             Code=a;
             System.out.println("Κωδικος:" + Code);
        }
  
      public void setComName(String ComName)
        {
        this.ComName=ComName; 
        }
      
      public void setModel(String Model)
        {
        this.Model=Model; 
        } 
      
      public void setColor(String Color)
        {
        this.Color=Color; 
        }  
      
      public void setHP(int HP)
        {
        this.HP=HP; 
        }
      
      public void setCc(int Cc)
        {
        this.Cc=Cc; 
        }
      
      public void setPrice(float Price)
        {
        this.Price=Price; 
        }
      
      public String getComName()
        {
            return ComName;
        }
      
      public String getModel()
        {
            return Model;
        }
      
      public String getColor()
        {
            return Color;
        }
      
      public int getHP()
        {
            return HP;
        }
      
      public int getCc()
        {
            return Cc;
        }
      public int getCode()
        {
            return Code;
        }
      
      public float getPrice()
        {
            return Price;
        }
          
}
