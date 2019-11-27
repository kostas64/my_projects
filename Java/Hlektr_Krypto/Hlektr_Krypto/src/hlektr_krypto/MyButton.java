
package hlektr_krypto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyButton extends JButton {
 Graphics g; 
 Grammata a;
 private javax.swing.JPopupMenu popup;
    public MyButton(Grammata c){
        a=c;
        setMargin(new Insets(0, 0, 0, 0));
        setBorder(null);      
        setFocusPainted(false);
        setPreferredSize(new Dimension(40,40));
        setFont(new Font("Arial",Font .BOLD,23));
        setContentAreaFilled(false);
        setVisible(true);
        setHorizontalTextPosition(SwingConstants.LEFT);
        setVerticalTextPosition(AbstractButton.BOTTOM);
    }
    
  
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g);
    
      if (a instanceof Aspro){
         
         g.setColor(Color.WHITE);
      }
      else if (a instanceof Mple){
         
          g.setColor(Color.BLUE);
      }
      else if (a instanceof Kokkino){
       
        g.setColor(Color.RED);
        
      }
     
      g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
      g.setColor(Color.BLACK);    
      g.drawString(Character.toString(a.getVal()), 5, 28);
      g.setFont(new Font("Arial",Font .PLAIN,12));
       g.drawString(Integer.toString(a.getPoint()),25,30);
     
     if (!getModel().isEnabled()) 
     {
         setBorderColor(g);         
     }
     
     if(getModel().isPressed())
     {
          setEnabled(false);
          if(a.getVal()=='?'){
              try {
                  pressed();
              } catch (IOException ex) {
                  Logger.getLogger(MyButton.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
     } 
     
   g.dispose();
    }
    
    public void setBorderColor(Graphics g)
    {
      g.setColor(Color.yellow);
      g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
      g.setColor(Color.BLACK);  
      g.setFont(new Font("Arial",Font .BOLD,23));
      g.drawString(Character.toString(a.getVal()), 5, 28);
      g.setFont(new Font("Arial",Font .PLAIN,12));
      g.drawString(Integer.toString(a.getPoint()),25,30);     
    }
    
    private void pressed() throws IOException {    
      
        Leitourgies l1 = new Leitourgies();
        int i;
        String[] choices= new String[24];
        for(i=0;i<24;i++){
            choices[i] =  Character.toString(l1.isostixia[i]);
        }
        
        String input = (String) JOptionPane.showInputDialog(null, "Choose now...",
        "Επιλεξε Γραμμα", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]); 
        
        for(i=0;i<24; i++){
            if(l1.isostixia[i]==input.charAt(0)){
                break;
            }
           
        }
        a.setVal(l1.isostixia[i]);
        a.setPoint(l1.pontoi_anagrama[i]);
        
        
        repaint();
        
    }
        
    
}
