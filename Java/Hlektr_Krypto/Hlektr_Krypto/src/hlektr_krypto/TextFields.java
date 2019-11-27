
package hlektr_krypto;
import java.awt.*;
import javax.swing.*;

public class TextFields extends JTextField{
    
    
    public TextFields(String str)
    {
        super(str);
        setBorder(null);
        setEditable(false);
        setPreferredSize(new Dimension(30,30));
        
    }
}
