
package hlektr_krypto;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


        
public class GFrame extends JFrame {
    private int swap1=3,swap_lin=3,swap_col=3,del=3,shuffle1=3;
    private int size=1,mege8os;
    private JPanel paixnidi, boi8ies, stats, elegxos;
    Grammata[][] tablo;
    MyButton button1;
    Graphics g;
    player player1= new player();
    Leitourgies l1;
    Container my_pane=getContentPane();
    JMenuItem enarksi=new JMenuItem("Εναρξη παιχνιδιου");
    JMenuItem eis_stoixewn=new JMenuItem("Εισαγωγη στοιχειων χρηστη");
    JMenuItem liksi=new JMenuItem("Ακυρωση/Τερματισμος παιχνιδιου"); 
    JMenuItem arxeio=new JMenuItem("Αναζητηση αρχειου λεξεων");
    JMenuItem helps=new JMenuItem("Ρυθμισεις βοηθειων");
    JMenuBar bara=new JMenuBar();
    JMenu menu=new JMenu("Menu");
    JMenuItem kanones=new JMenuItem("Κανονες παιχνιδιου");
    JMenuItem about=new JMenuItem("About");  
    JMenu ergaleia=new JMenu("Εργαλεια");
    
    public GFrame(Leitourgies l2){
        super("ΚΡΥΠΤΟΛΕΞΟ");
        l1=l2;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLocationRelativeTo(null);       
        setVisible(true);
        createmenu();
       
       //Grammata tablo[][]=new Grammata[(int)Math.sqrt(mege8os)][(int)Math.sqrt(mege8os)]; 
        //pack();     
    }
   
        
  
    private void createmenu(){
        
       
        bara.setVisible(true);
        
                                //Εναρξη παιχνιδιου
        enarksi.addActionListener(ActionEvent ->{
             switch(size)              //Επιλογη χρηστη για μεγεθος ταμπλο
        {
            case 1: 
                tablo = new Grammata[5][5];
                mege8os=25;
                break;
            case 2:   
                tablo = new Grammata[8][8];
                mege8os=64;
                break;
            case 3:
                tablo = new Grammata[10][10];
                mege8os=100;
                break;
        } 
             tablo = l1.Gemisma_tablo(size,l1.pontoi_anagrama,l1.isostixia);
        paixnidi=new JPanel();
        boi8ies=new JPanel();
        stats=new JPanel();
        elegxos=new JPanel();
        paixnidi.setVisible(true);
        boi8ies.setVisible(true);
        stats.setVisible(true);
        elegxos.setVisible(true);
        JPanel panel[][] = new JPanel[(int)Math.sqrt(mege8os)][(int)Math.sqrt(mege8os)];
        
        GridLayout my_layout=new GridLayout(2,2);
        my_pane.setLayout(my_layout);
        JPanel ctablo=new JPanel();
        ctablo.setLayout(new GridLayout((int)Math.sqrt(mege8os),(int)Math.sqrt(mege8os))); 
        
        for(int i=0; i<(int)Math.sqrt(mege8os); i++)
            {
            
            for(int y=0; y<(int)Math.sqrt(mege8os); y++)
                 {
         
            button1 = new MyButton(tablo[i][y]);
            repaint();
          
           
            panel[i][y] = new JPanel();
            panel[i][y].add(button1);
             panel[i][y].setVisible(true);
            ctablo.add(panel[i][y]);
             
                 }
            }
       
         ImageIcon check_b = new ImageIcon("check_button.png");
         Image img;
         if(size==1)
                 {
                     img=getScaledImage(check_b.getImage(),100,100);
                 }
         else if(size==2)
         {
            img=getScaledImage(check_b.getImage(),150,150); 
         }
         else
             img=getScaledImage(check_b.getImage(),180,180);
        
         ImageIcon icon=new ImageIcon(img);
         JLabel label_icon = new JLabel(icon,JLabel.LEADING);
      
         elegxos.add(label_icon);
        
         ctablo.setVisible(true);
         paixnidi.add(ctablo);
         JLabel label = new JLabel("Βοήθειες");
          JPanel row1= new JPanel();
          JPanel row2 = new JPanel();
          JPanel row3 = new JPanel();
          JPanel row4= new JPanel();
          JPanel row5 = new JPanel();
          JPanel row6 = new JPanel();
          JPanel row11= new JPanel();
          JPanel row22 = new JPanel();
          JPanel row33 = new JPanel();
          JPanel row44= new JPanel();
          JPanel row55 = new JPanel();
          JPanel row66 = new JPanel();
          
         FlowLayout layout= new FlowLayout();
         HelpButton del_line= new HelpButton("Διαγραφή γραμμής");
         HelpButton shuffle_line= new HelpButton("Αναδιάταξη Γραμμής");
         HelpButton shuffle_col= new HelpButton("Αναδιάταξη στήλης");
         HelpButton shuffle= new HelpButton("Αναδιάταξη ταβλό");
         HelpButton swap= new HelpButton("Εναλλαγή γραμμάτων");
         JTextField delete_line= new JTextField("0");
         JTextField shuf_line= new JTextField("0");
         JTextField shuf_col= new JTextField("0");
         JTextField label4= new JTextField();
         JTextField label5= new JTextField();
         TextFields help1 = new TextFields(del+"/"+del);
         TextFields help2 = new TextFields(swap_lin+"/"+swap_lin);
         TextFields help3 = new TextFields(swap_col+"/"+swap_col);
         TextFields help4 = new TextFields(shuffle1+"/"+shuffle1);
         TextFields help5 = new TextFields(swap1+"/"+swap1);
         delete_line.setPreferredSize(new Dimension(20,30));
         delete_line.setBorder(null);
         shuf_line.setPreferredSize(new Dimension(20,30));
         shuf_line.setBorder(null);
         shuf_col.setPreferredSize(new Dimension(20,30));
         shuf_col.setBorder(null);
         label.setFont(new Font("Arial",Font .BOLD,18));
         
         label4.setPreferredSize(new Dimension(20,30));
         label5.setPreferredSize(new Dimension(20,30));
         label4.setBorder(null);
         label5.setBorder(null);
         label4.setEditable(false);
         label5.setEditable(false);
         row1.add(label);
         
         
         row2.add(del_line);
         row2.add(delete_line);
         row2.add(help1);
         
         row3.add(shuffle_line);
         row3.add(shuf_line);
         row3.add(help2);
         
         row4.add(shuffle_col);
         row4.add(shuf_col);
         row4.add(help3);
         
         row5.add(shuffle);
         row5.add(label4);
         row5.add(help4);
         
         row6.add(swap);
         row6.add(label5);
         row6.add(help5);
         
         row1.setLayout(new FlowLayout(FlowLayout.RIGHT));
         row2.setLayout(layout);
         row3.setLayout(layout);
         row4.setLayout(layout);
         row5.setLayout(layout);
         row6.setLayout(layout);
         
         row11.setLayout(layout);
         row22.setLayout(layout);
         row33.setLayout(layout);
         row44.setLayout(layout);
         row55.setLayout(layout);
         row66.setLayout(layout);
         
         
         
         JLabel onoma = new JLabel(player1.getName());
        onoma.setFont(new Font("Arial",Font .BOLD,18));
        JLabel stoxos = new JLabel("Στόχος");
        TextFields p_nikis= new TextFields(Integer.toString(player1.getPontoi_nikis()));
        JLabel ba8moi = new JLabel("Συνολική βαθμολογία");
        TextFields pontoi= new TextFields(Integer.toString(player1.getPontoi()));
        JLabel ba8moi_l = new JLabel("Βα8μολογία λέξης");
        TextFields p_l= new TextFields(Integer.toString(0));
        JLabel ari8_l = new JLabel("Λέξεις που βρέθηκαν");
        TextFields lekseis_n= new TextFields("0"+"/"+player1.getLexeis_nikis());
        JLabel tel_leks = new JLabel("Bρήκες τη λεξη:");
        TextFields leksi= new TextFields("");
        leksi.setPreferredSize(new Dimension(70,30));
         row11.add(onoma);
         row22.add(stoxos);
         row22.add(p_nikis);
         row33.add(ba8moi);
         row33.add(pontoi);
         row44.add(ba8moi_l);
         row44.add(p_l);
         row55.add(ari8_l);
         row55.add(lekseis_n);
         row66.add(tel_leks);
         row66.add(leksi);
         boi8ies.add(row1);
         boi8ies.add(row2);
         boi8ies.add(row3);
         boi8ies.add(row4);
         boi8ies.add(row5);
         boi8ies.add(row6);
         boi8ies.setLayout(new GridLayout(6,1));
          stats.add(row11);
          stats.add(row22);
          stats.add(row33);
          stats.add(row44);
          stats.add(row55);
          stats.add(row66);
          stats.setLayout(new GridLayout(6,1));
         my_pane.add(paixnidi);
         my_pane.add(boi8ies);
          my_pane.add(elegxos);
         my_pane.add(stats);
         
         enarksi.setEnabled(false);
         eis_stoixewn.setEnabled(false);
         helps.setEnabled(false);
         arxeio.setEnabled(false);
        
        
               
       
        setContentPane(my_pane); 
         
          pack();
        
        });
        
        
                    //Ακυρωση/Τερματισμος παιχνιδιου
        liksi.addActionListener(ActionEvent -> {
            enarksi.setEnabled(true);
         eis_stoixewn.setEnabled(true);
         helps.setEnabled(true);
         arxeio.setEnabled(true);
           my_pane.removeAll();
           revalidate();
           repaint();
        });
       
        
        
                  //Εισαγωγη στοιχειων χρηστη
        eis_stoixewn.addActionListener(ActionEvent -> {
            JPanel row1= new JPanel();
            JPanel row2 = new JPanel();
            JPanel row3 = new JPanel();
            JLabel label1= new JLabel("Δωσε το ονομα σου");
            JLabel label2 = new JLabel("Επίλεξε τις διαστάσεις του ταμπλο");
            JTextField field1 = new JTextField(20);
            
            JRadioButton button = new JRadioButton("5x5");
            button.setActionCommand("1");
            JRadioButton button2 = new JRadioButton("8x8");
            button2.setActionCommand("2");
            JRadioButton button3 = new JRadioButton("10x10");
            button3.setActionCommand("3");
            
            ButtonGroup group= new ButtonGroup();
            button.setSelected(true);
            group.add(button);
            group.add(button2);
            group.add(button3);
            
          
           
            row1.add(label1);
            row2.add(label2);
            row3.add(button);
            row3.add(button2);
            row3.add(button3);
          
            row1.add(field1);  
              
             
            GridLayout layout = new GridLayout(3,1);
            Container pane = getContentPane();
            pane.setLayout(layout);
            FlowLayout flowlayout = new FlowLayout();
            row1.setLayout(flowlayout);
          
            row3.setLayout(flowlayout);
          
            pane.add(row1);
            pane.add(row2);
            pane.add(row3);
         
            setContentPane(pane);
            
            
             int result = JOptionPane.showConfirmDialog(null,pane,"Εισαγωγή στοιχειων παίχτη",JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                 String name=field1.getText();
                 size =Integer.parseInt(group.getSelection().getActionCommand());     
              if(!(field1.getText().equals("")))
                   player1.setName(name);
              
            }           
            pane.removeAll();
            
            
        });
        
                                //Αναζητηση αρχειου λεξεων
        arxeio.addActionListener(ActionEvent -> {
            JButton open = new JButton();
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new java.io.File("C:/"));
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION)
            {
                try {
                    String path =  fc.getSelectedFile().getAbsolutePath();
                   System.out.print(path);
                   l1.Gemisma(path) ;
                } catch (IOException ex) {
                    Logger.getLogger(GFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
                              //Ρυθμισεις βοηθειων
        helps.addActionListener(ActionEvent->{
            JPanel row0=new JPanel();  
            JPanel row1=new JPanel();            
            JPanel row2=new JPanel();           
            JPanel row3=new JPanel();            
            JPanel row4=new JPanel();            
            JPanel row5=new JPanel();
            JLabel Default = new JLabel("Ολες οι βοηθειες εχουν προκαθορισμενη τιμη 3");
            
            JLabel swap=new JLabel("αλλαγη γραμματων μεταξυ τους", JLabel.RIGHT);
            JTextField field1 = new JTextField(1);
            JLabel mperdema_seira=new JLabel("Μπερδεμα Γραμμης", JLabel.RIGHT);
              JTextField field2= new JTextField(1);
            JLabel mperdema_stili=new JLabel("Μπερδεμα Στηλης", JLabel.RIGHT);
              JTextField field3 = new JTextField(1);
            JLabel shuffle=new JLabel("Μπερδεμα", JLabel.RIGHT);
              JTextField field4 = new JTextField(1);
            JLabel delete=new JLabel("Διαγραφη γραμμης", JLabel.RIGHT);
                JTextField field5 = new JTextField(1);
            row0.add(Default);
            row1.add(swap);
            row2.add(mperdema_seira);
            row3.add(mperdema_stili);
            row4.add(shuffle);
            row5.add(delete);
            row1.add(field1);  
              row2.add(field2);  
              row3.add(field3);  
              row4.add(field4);  
              row5.add(field5);  
            GridLayout layout = new GridLayout(6,1);
            Container pane = getContentPane();
            pane.setLayout(layout);
            FlowLayout flowlayout = new FlowLayout();
            row1.setLayout(flowlayout);
            row2.setLayout(flowlayout);
            row3.setLayout(flowlayout);
            row4.setLayout(flowlayout);
            row5.setLayout(flowlayout);
            pane.add(row0);
            pane.add(row1);
            pane.add(row2);
            pane.add(row3);
            pane.add(row4);
            pane.add(row5);
            setContentPane(pane);
        
        
            
            int result = JOptionPane.showConfirmDialog(null,pane,"Ρυθμιση βοηθειων",JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                 if(!(null== field1.getText()))
                     swap1 = Integer.parseInt(field1.getText());
                 if(!(null== field2.getText()))
                     swap_lin = Integer.parseInt(field2.getText());
                 if(!(null== field3.getText()))
                     swap_col = Integer.parseInt(field3.getText());
                 if(!(null== field4.getText()))
                     shuffle1 = Integer.parseInt(field4.getText());
                 if(!(null== field5.getText()))    
                     del = Integer.parseInt(field5.getText());  
            }
                    pane.removeAll();
        }
        );
        
                                          //Κανονες παιχνιδιου
        kanones.addActionListener(ActionEvent->{
        JOptionPane.showMessageDialog(null,"oi KANONES","Κανόνες Παιχνιδιού",JOptionPane.INFORMATION_MESSAGE);
        }
        );
                                               //about
        about.addActionListener(ActionEvent->{
        JOptionPane.showMessageDialog(null, "Χαραλαμπος Αντωνιαδης - icsd10011\nΕυκαρπιδης Κωνσταντινος - icsd15051","Partners",JOptionPane.INFORMATION_MESSAGE);
        }
        );
        
        JMenuItem eksodos=new JMenuItem("Εξοδος");                                      //Εξοδος
        eksodos.addActionListener((ActionEvent)->{
            System.exit(0);
        });
        
        menu.add(enarksi);
        menu.add(liksi);
        menu.add(eis_stoixewn);
        menu.add(arxeio);
        menu.add(helps);
        menu.add(eksodos);
                                               //Εργαλεια
        ergaleia.add(kanones);
        ergaleia.add(about);
        
        bara.add(menu);
        bara.add(ergaleia);
        setJMenuBar(bara);
    }
   private void RightClicked(MouseEvent e){
       System.out.print("PEOS");
        if(e.getModifiers()==MouseEvent.BUTTON3_MASK){
            for(int i=0; i<(int)Math.sqrt(mege8os); i++)
            {
            for(int y=0; y<(int)Math.sqrt(mege8os); y++)
                 {
                 button1.setEnabled(true);
                 
                 }
            }
          
        }
        
    } 
    
    private Image getScaledImage(Image srcImg, int w, int h)
        {
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImg.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();

    return resizedImg;
        }

    public static void listDictionary()throws IOException
        {
    BufferedReader br = new BufferedReader(new FileReader("rules.txt"));
    String aLineFromFile = null;
    while ((aLineFromFile = br.readLine()) != null){
            JOptionPane.showMessageDialog(null, aLineFromFile);
    }        
    br.close();
        }   
    
  
}


