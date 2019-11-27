
package hlektr_krypto;
import java.awt.*;
import javax.swing.*;

public class HelpButton extends JButton{
    
    public HelpButton(String str)
    {
        super(str);
        setMargin(new Insets(0, 0, 0, 0));
        setPreferredSize(new Dimension(150,30));
        setFont(new Font("Arial",Font .PLAIN,12));  
        setVisible(true);
       // setHorizontalTextPosition(SwingConstants.LEFT);
     // setVerticalTextPosition(AbstractButton.BOTTOM);
    }
}
