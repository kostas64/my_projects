
package hlektr_krypto;
import java.awt.*;
import javax.swing.*;

public class TextFields extends JTextField{
    
    
    public TextFields(String str)   //Constructors
    {
        super(str); //Customization
        setBorder(null);
        setEditable(false);
        setPreferredSize(new Dimension(30,30));
        
    }
    
    public TextFields()
    {
        setBorder(null);
        setEditable(false);
        setPreferredSize(new Dimension(30,30));
    }
}
