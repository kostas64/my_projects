package hlektr_krypto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GFrame extends JFrame {

    private int swap1 = 3, swap_lin = 3, swap_col = 3, del = 3, shuffle1 = 3, lekseis_nikis = 0; //Default τιμες βοηθειων,lekseis nikis -> μετρητης λεξεων που βρηκε
    private int size = 1, mege8os;         //size=1 (5x5 ταμπλο) κτξ. ,mege8os=αριθμος κουμπιων για ταμπλο
    private JPanel paixnidi, boi8ies, stats, elegxos;       //τα jpanel που περιεχονται στο jframe 
    boolean find;                                           //μεταβλητη εκχωρησης για το αν βρηκε τη λεξη  
    Grammata[][] tablo;
    ArrayList<Grammata> lexi = new ArrayList<Grammata>();         //Εδω μπαινουν ολα τα γραμματα που εχει επιλεξει ο χρηστης
    ArrayList<Integer> k = new ArrayList<Integer>();              //οι x συντεταγμενες αυτων
    ArrayList<Integer> l = new ArrayList<Integer>();               //οι y συντεταγμενες αυτων
    MyButton button1;
    Graphics g;
    player player1 = new player();
    Leitourgies l1;
    Container my_pane = getContentPane();
    JMenuItem enarksi = new JMenuItem("Εναρξη παιχνιδιου");
    JMenuItem eis_stoixewn = new JMenuItem("Εισαγωγη στοιχειων χρηστη");
    JMenuItem liksi = new JMenuItem("Ακυρωση/Τερματισμος παιχνιδιου");
    JMenuItem arxeio = new JMenuItem("Αναζητηση αρχειου λεξεων");
    JMenuItem helps = new JMenuItem("Ρυθμισεις βοηθειων");
    JMenuBar bara = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem kanones = new JMenuItem("Κανονες παιχνιδιου");
    JMenuItem about = new JMenuItem("About");
    JMenu ergaleia = new JMenu("Εργαλεια");
    HelpButton del_line = new HelpButton("Διαγραφή γραμμής");
    HelpButton shuffle_line = new HelpButton("Αναδιάταξη Γραμμής");
    HelpButton shuffle_col = new HelpButton("Αναδιάταξη στήλης");
    HelpButton shuffle = new HelpButton("Αναδιάταξη ταβλό");
    HelpButton swap = new HelpButton("Εναλλαγή γραμμάτων");
    TextFields help1 = new TextFields();
    TextFields help2 = new TextFields();
    TextFields help3 = new TextFields();
    TextFields help4 = new TextFields();
    TextFields help5 = new TextFields();
    TextFields p_nikis = new TextFields(Integer.toString(player1.getPontoi_nikis()));
    TextFields pontoi = new TextFields(Integer.toString(player1.getPontoi()));
    TextFields lekseis_n = new TextFields("0" + "/" + player1.getLexeis_nikis());
    TextFields p_l = new TextFields(Integer.toString(0));
    TextFields leksi = new TextFields("");
    int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0;                     //Μετρητες βοηθειων
    JPanel ctablo = new JPanel();

    public GFrame(Leitourgies l2) {     // Constructor
        super("ΚΡΥΠΤΟΛΕΞΟ");
        l1 = l2;
        liksi.setEnabled(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        createmenu();

        //Grammata tablo[][]=new Grammata[(int)Math.sqrt(mege8os)][(int)Math.sqrt(mege8os)]; 
        //pack();     
    }

    private void createmenu() {

        bara.setVisible(true);

        //Εναρξη παιχνιδιου
        enarksi.addActionListener(ActionEvent -> {
            liksi.setEnabled(true);
            switch (size) //Επιλογη χρηστη για μεγεθος ταμπλο
            {
                case 1:
                    tablo = new Grammata[5][5];
                    mege8os = 25;
                    break;
                case 2:
                    tablo = new Grammata[8][8];
                    mege8os = 64;
                    break;
                case 3:
                    tablo = new Grammata[10][10];
                    mege8os = 100;
                    break;
            }
            tablo = l1.Gemisma_tablo(size);
            paixnidi = new JPanel();
            boi8ies = new JPanel();
            stats = new JPanel();
            elegxos = new JPanel();
            paixnidi.setVisible(true);
            boi8ies.setVisible(true);
            stats.setVisible(true);
            elegxos.setVisible(true);
            JPanel panel[][] = new JPanel[(int) Math.sqrt(mege8os)][(int) Math.sqrt(mege8os)];

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {

                    if (me.getButton() == 3) {
                        for (int i = 0; i < (int) Math.sqrt(mege8os); i++) {
                            for (int y = 0; y < (int) Math.sqrt(mege8os); y++) {
                                panel[i][y].removeAll();
                                button1 = new MyButton(tablo[i][y]);
                                button1.putClientProperty("column", i);        //δινουμε το i,j σε καθε jpanel 
                                button1.putClientProperty("row", y);

                                button1.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {                //Κανουμε αρχικοποιηση του ταμπλο μετα το δεξι κλικ
                                        JButton btnPanel = (JButton) e.getSource();
                                        k.add(Integer.parseInt(btnPanel.getClientProperty("row").toString()));
                                        l.add(Integer.parseInt(btnPanel.getClientProperty("column").toString()));

                                    }
                                });

                                panel[i][y].add(button1);
                                panel[i][y].setVisible(true);
                                k.removeAll(k);
                                l.removeAll(l);
                                lexi.removeAll(lexi);
                            }
                        }
                        ctablo.revalidate();
                        repaint();
                    }

                }
            });

            GridLayout my_layout = new GridLayout(2, 2);           //Δημιουργια Grid
            my_pane.setLayout(my_layout);                       //Προσθεση του grid στο Container

            ctablo.setLayout(new GridLayout((int) Math.sqrt(mege8os), (int) Math.sqrt(mege8os)));

            for (int i = 0; i < (int) Math.sqrt(mege8os); i++) {
                for (int y = 0; y < (int) Math.sqrt(mege8os); y++) {

                    button1 = new MyButton(tablo[i][y]);

                    panel[i][y] = new JPanel();

                    button1.putClientProperty("column", i);
                    button1.putClientProperty("row", y);
                    panel[i][y].add(button1);
                    panel[i][y].setVisible(true);

                    button1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            JButton btnPanel = (JButton) e.getSource();
                            k.add(Integer.parseInt(btnPanel.getClientProperty("row").toString()));         //παιρνουμε το i,j του jpanel που επιλεξε ο χρηστης
                            l.add(Integer.parseInt(btnPanel.getClientProperty("column").toString()));

                        }
                    });

                    ctablo.add(panel[i][y]);
                }
            }
            repaint();

            JButton check = new JButton();          //Δημιουργια κουμπιου ελεγχου λεξης
            ImageIcon check_b = new ImageIcon("check_button.png");
            check.setMargin(new Insets(0, 0, 0, 0));
            check.setBorder(null);
            check.setFocusPainted(false);

            Image img;
            if (size == 1) {
                img = getScaledImage(check_b.getImage(), 100, 100);            //κληση της συναρτησης μας getScaledImage οπου το κανουμε resize αναλογα με την επιλογη του χρηστη
                check.setPreferredSize(new Dimension(100, 100));
            } else if (size == 2) {
                img = getScaledImage(check_b.getImage(), 150, 150);
                check.setPreferredSize(new Dimension(150, 150));
            } else {
                img = getScaledImage(check_b.getImage(), 180, 180);
                check.setPreferredSize(new Dimension(180, 180));
            }

            ImageIcon icon = new ImageIcon(img);
            check.setIcon(icon);

            check.addActionListener(new ActionListener() {     //Listener για οταν παταει το κουμπι του ελεγχου
                @Override
                public void actionPerformed(ActionEvent e) {
                    int c = 0;
                    String str = null;

                    for (Integer i : k) {                  //Παιρνει ολα τα γραμματα και τα προσθετει στο arraylist lexi
                        if (c == 0) {
                            str = Character.toString(tablo[l.get(c)][k.get(c)].getVal());
                            lexi.add(tablo[l.get(c)][k.get(c)]);
                        } else {
                            str += tablo[l.get(c)][k.get(c)].getVal();
                            lexi.add(tablo[l.get(c)][k.get(c)]);
                        }
                        c++;
                    }

                    find = l1.CheckWord(str);      //καλει τη συναρτηση για να ελεγξει αν η λεξη ειναι σωστη
                    if (find) //αν ειναι ενημερωνει τα στατιστικα και κανει ολες τις υπολοιπες λειτουργιες
                    {
                        lekseis_nikis++;
                        System.out.println(l1.Ypologismos(lexi));
                        player1.addPontoi(l1.Ypologismos(lexi));
                        pontoi.setText(String.valueOf((player1.getPontoi())));
                        lekseis_n.setText(lekseis_nikis + "/" + "10");
                        leksi.setText(str);
                        p_l.setText(String.valueOf(l1.Ypologismos(lexi)));
                        c = 0;
                        int red = 0, blue = 0, balader = 0;
                        for (Integer i : k) {
                            panel[l.get(c)][k.get(c)].getComponent(0).setEnabled(true);
                            panel[l.get(c)][k.get(c)].removeAll();
                            tablo[l.get(c)][k.get(c)] = null;
                            c++;
                        }
                        for (int i = 0; i < (int) Math.sqrt(mege8os); i++) {
                            for (int y = 0; y < (int) Math.sqrt(mege8os); y++) {
                                if (tablo[i][y] instanceof Mple) {
                                    blue++;
                                } else if (tablo[i][y] instanceof Kokkino) {
                                    red++;
                                }
                                if (tablo[i][y] == null) {
                                    continue;
                                } else if (tablo[i][y].getVal() == '?') {
                                    balader++;
                                }
                            }
                        }
                        tablo = l1.create_tablo(tablo, k.size(), blue, red, balader, l, k);

                        for (int i = 0; i < k.size(); i++) //Φτιαχνει τα κουμπια και αντικαταθηστα τα γραμματα της λεξης που βρηκε 
                        {

                            button1 = new MyButton(tablo[l.get(i)][k.get(i)]);

                            button1.putClientProperty("column", l.get(i));
                            button1.putClientProperty("row", k.get(i));
                            panel[l.get(i)][k.get(i)].add(button1);
                            button1.setEnabled(true);
                            panel[l.get(i)][k.get(i)].setVisible(true);
                            button1.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    JButton btnPanel = (JButton) e.getSource();
                                    k.add(Integer.parseInt(btnPanel.getClientProperty("row").toString()));         //παιρνουμε το i,j του jpanel που επιλεξε ο χρηστης
                                    l.add(Integer.parseInt(btnPanel.getClientProperty("column").toString()));
                                }
                            });
                            repaint();
                        }
                        k.removeAll(k);
                        l.removeAll(l);
                        if (player1.getLexeis_nikis() == lekseis_nikis || player1.getPontoi_nikis() <= player1.getPontoi()) { //Συνθηκη για να ληξει το παιχνιδι
                            if (player1.getPontoi_nikis() <= player1.getPontoi()) {
                                JOptionPane.showMessageDialog(null, "Συγχαρητηρια, ΚΕΡΔΙΣΕΣ");
                            } else {
                                JOptionPane.showMessageDialog(null, "Λυπαμαι Ηττηθηκες οικτρα!!");
                            }
                            liksi.doClick();   //Κλικαρουμε την ληξη του παιχνιδιου απο το JMenu
                        }

                        ctablo.revalidate();
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Η ΛΕΞΗ ΠΟΥ ΨΑΧΝΕΙΣ ΔΕΝ ΥΠΑΡΧΕΙ");
                    }
                }

            });

            elegxos.add(check);
            ctablo.setVisible(true);
            paixnidi.add(ctablo);
            JLabel label = new JLabel("Βοήθειες");
            JPanel row1 = new JPanel();    //Πανελ βοηθειων
            JPanel row2 = new JPanel();
            JPanel row3 = new JPanel();
            JPanel row4 = new JPanel();
            JPanel row5 = new JPanel();
            JPanel row6 = new JPanel();
            JPanel row11 = new JPanel();
            JPanel row22 = new JPanel();
            JPanel row33 = new JPanel();
            JPanel row44 = new JPanel();
            JPanel row55 = new JPanel();
            JPanel row66 = new JPanel();
            FlowLayout layout = new FlowLayout();

            JTextField delete_line = new JTextField("0");
            del_line.addActionListener(new ActionListener() {  //Listener για το κουμπι "Διαγραφή Γραμμής"
                @Override
                public void actionPerformed(ActionEvent e) {

                    if ((Integer.parseInt(delete_line.getText()) <= 0) || (Integer.parseInt(delete_line.getText()) > (int) Math.sqrt(mege8os))) {
                        JOptionPane.showMessageDialog(null, "Διάλεξε σειρά >0 και <" + (int) Math.sqrt(mege8os), "Προσοχή", JOptionPane.OK_OPTION);
                    } else {
                        c1 = l1.delete_line(tablo, mege8os, Integer.parseInt(delete_line.getText()));
                        if ((del - c1) == 0) //απενεργοποιουμε το κουμπι οταν δεν εχει αλλη βοηθεια
                        {
                            del_line.setEnabled(false);
                        }
                        help1.setText(del - c1 + "/" + del);
                        ctablo.revalidate();
                        repaint();
                    }
                }
            });
            JTextField shuf_line = new JTextField("0");
            shuffle_line.addActionListener(new ActionListener() {    //Listener για το κουμπι "Αναδιάταξη Γραμμης"
                @Override
                public void actionPerformed(ActionEvent e) {

                    if ((Integer.parseInt(shuf_line.getText()) <= 0) || (Integer.parseInt(shuf_line.getText()) > (int) Math.sqrt(mege8os))) {
                        JOptionPane.showMessageDialog(null, "Διάλεξε σειρά >0 και <" + (int) Math.sqrt(mege8os), "Προσοχή", JOptionPane.OK_OPTION);
                    } else {
                        for (int i = 0; i < Math.sqrt(mege8os); i++) {
                            panel[Integer.parseInt(shuf_line.getText()) - 1][i].removeAll();
                        }
                        c2 = l1.shuffle_line(tablo, mege8os, Integer.parseInt(shuf_line.getText()));
                        for (int i = 0; i < Math.sqrt(mege8os); i++) {
                            button1 = new MyButton(tablo[Integer.parseInt(shuf_line.getText()) - 1][i]);
                            panel[Integer.parseInt(shuf_line.getText()) - 1][i].add(button1);
                            panel[Integer.parseInt(shuf_line.getText()) - 1][i].setVisible(true);

                        }
                        if ((swap_lin - c2) == 0) //απενεργοποιουμε το κουμπι οταν δεν εχει αλλη βοηθεια
                        {
                            shuffle_line.setEnabled(false);
                        }
                        help2.setText(swap_lin - c2 + "/" + swap_lin);
                        ctablo.revalidate();
                        repaint();
                    }
                }
            });
            JTextField shuf_col = new JTextField("0");
            shuffle_col.addActionListener(new ActionListener() {    //Listener για το κουμπι "Αναδιάταξη Γραμμης"
                @Override
                public void actionPerformed(ActionEvent e) {

                    if ((Integer.parseInt(shuf_col.getText()) <= 0) || (Integer.parseInt(shuf_col.getText()) > (int) Math.sqrt(mege8os))) {
                        JOptionPane.showMessageDialog(null, "Διάλεξε σειρά >0 και <" + (int) Math.sqrt(mege8os), "Προσοχή", JOptionPane.OK_OPTION);
                    } else {
                        for (int i = 0; i < Math.sqrt(mege8os); i++) {
                            panel[i][Integer.parseInt(shuf_col.getText()) - 1].removeAll();
                        }
                        c3 = l1.shuffle_column(tablo, mege8os, Integer.parseInt(shuf_col.getText()));
                        for (int i = 0; i < Math.sqrt(mege8os); i++) {
                            button1 = new MyButton(tablo[i][Integer.parseInt(shuf_col.getText()) - 1]);
                            panel[i][Integer.parseInt(shuf_col.getText()) - 1].add(button1);
                            panel[i][Integer.parseInt(shuf_col.getText()) - 1].setVisible(true);
                        }
                        if ((swap_col - c3) == 0) //απενεργοποιουμε το κουμπι οταν δεν εχει αλλη βοηθεια
                        {
                            shuffle_col.setEnabled(false);
                        }
                        
                        help3.setText(swap_col - c3 + "/" + swap_col);
                        ctablo.revalidate();
                        repaint();
                    }
                }
            });

            JTextField label4 = new JTextField();
            shuffle.addActionListener(new ActionListener() {    //Listener για το κουμπι "Αναδιάταξη Ταμπλο"

                @Override
                public void actionPerformed(ActionEvent e) {

                    for (int i = 0; i < Math.sqrt(mege8os); i++) {
                        for (int j = 0; j < Math.sqrt(mege8os); j++) {
                            panel[i][j].removeAll();      //αφαιρει τα παντα απο τον πινακα των jpanel
                        }
                    }
                    c4 = l1.shuffle(tablo, mege8os);         //κανει το shuffle
                    for (int i = 0; i < Math.sqrt(mege8os); i++) {
                        for (int j = 0; j < Math.sqrt(mege8os); j++) {
                            button1 = new MyButton(tablo[i][j]);
                            panel[i][j].add(button1);                //και τα ξαναπερναει
                            panel[i][j].setVisible(true);
                        }
                    }
                    if (shuffle1 - c4 == 0) //απενεργοποιουμε το κουμπι οταν δεν εχει αλλη βοηθεια
                    {
                        shuffle.setEnabled(false);
                    }
                    help4.setText(shuffle1 - c4 + "/" + shuffle1);
                    ctablo.revalidate();
                    repaint();
                }
            });

            JTextField label5 = new JTextField();
            swap.addActionListener(new ActionListener() {          //Listener για το κουμπι "Εναλλαγη γραμματων"
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!(k.size() == 2)) {
                        JOptionPane.showMessageDialog(null, "Διαλεξε MONO τα 2 γραμματα που θελεις να αλλαξεις μεταξύ τους", "Ενναλαγη γραμματων", JOptionPane.WARNING_MESSAGE);
                    } else {
                        c5++;
                        help5.setText(swap1 - c5 + "/" + swap1);
                        if (swap1 - c5 == 0) //απενεργοποιουμε το κουμπι οταν δεν εχει αλλη βοηθεια
                        {
                            swap.setEnabled(false);
                        }
                        l1.swap(l.get(0), k.get(0), l.get(1), k.get(1));
                        panel[l.get(0)][k.get(0)].removeAll();
                        panel[l.get(1)][k.get(1)].removeAll();

                        for (int i = 0; i < k.size(); i++) //Αρχικοποιηση των κουμπιων που θα αλλαξουν μεταξυ τους
                        {
                            button1 = new MyButton(tablo[l.get(i)][k.get(i)]);
                            button1.putClientProperty("column", l.get(i));
                            button1.putClientProperty("row", k.get(i));
                            panel[l.get(i)][k.get(i)].add(button1);
                            button1.setEnabled(true);
                            panel[l.get(i)][k.get(i)].setVisible(true);
                            button1.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    JButton btnPanel = (JButton) e.getSource();
                                    k.add(Integer.parseInt(btnPanel.getClientProperty("row").toString()));         //παιρνουμε το i,j του jpanel που επιλεξε ο χρηστης
                                    l.add(Integer.parseInt(btnPanel.getClientProperty("column").toString()));
                                }
                            });
                        }
                        ctablo.revalidate();
                        repaint();
                    }
                    k.removeAll(k);
                    l.removeAll(l);
                }
            });

            help1.setText(del - c1 + "/" + del);
            help2.setText(swap_lin - c2 + "/" + swap_lin);
            help3.setText(swap_col - c3 + "/" + swap_col);
            help4.setText(shuffle1 - c4 + "/" + shuffle1);
            help5.setText(swap1 - c5 + "/" + swap1);
            delete_line.setPreferredSize(new Dimension(20, 30));        //Μορφοποιηση κουμπιων
            delete_line.setBorder(null);
            shuf_line.setPreferredSize(new Dimension(20, 30));
            shuf_line.setBorder(null);
            shuf_col.setPreferredSize(new Dimension(20, 30));
            shuf_col.setBorder(null);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label4.setPreferredSize(new Dimension(20, 30));
            label5.setPreferredSize(new Dimension(20, 30));
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

            JLabel onoma = new JLabel(player1.getName());      //Grid(2,2) Σταταστικα
            onoma.setFont(new Font("Arial", Font.BOLD, 18));
            JLabel stoxos = new JLabel("Στόχος");

            JLabel ba8moi = new JLabel("Συνολική βαθμολογία");

            JLabel ba8moi_l = new JLabel("Βα8μολογία λέξης");

            JLabel ari8_l = new JLabel("Λέξεις που βρέθηκαν");

            JLabel tel_leks = new JLabel("Bρήκες τη λεξη:");

            leksi.setPreferredSize(new Dimension(70, 30));
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
            boi8ies.setLayout(new GridLayout(6, 1));
            stats.add(row11);
            stats.add(row22);
            stats.add(row33);
            stats.add(row44);
            stats.add(row55);
            stats.add(row66);
            stats.setLayout(new GridLayout(6, 1));
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
        liksi.addActionListener(ActionEvent -> {            //Listener για οταν παταει Ακυρωση/Τερματισμος παιχνιδιου
            liksi.setEnabled(false);
            enarksi.setEnabled(true);
            eis_stoixewn.setEnabled(true);
            helps.setEnabled(true);
            arxeio.setEnabled(true);

            paixnidi.removeAll();
            boi8ies.removeAll();
            elegxos.removeAll();
            stats.removeAll();

            l1.destroy();
            my_pane.removeAll();
            revalidate();
            repaint();
            dispose();

            GFrame frame = new GFrame(l1);
        });

        //Εισαγωγη στοιχειων χρηστη
        eis_stoixewn.addActionListener(ActionEvent -> {          //Listener για οταν παταει "Εισαγωγη στοιχειων χρηστη"
            JPanel row1 = new JPanel();
            JPanel row2 = new JPanel();
            JPanel row3 = new JPanel();
            JLabel label1 = new JLabel("Δωσε το ονομα σου");
            JLabel label2 = new JLabel("Επίλεξε τις διαστάσεις του ταμπλο");
            JTextField field1 = new JTextField(20);

            JRadioButton button = new JRadioButton("5x5");
            button.setActionCommand("1");
            JRadioButton button2 = new JRadioButton("8x8");
            button2.setActionCommand("2");
            JRadioButton button3 = new JRadioButton("10x10");
            button3.setActionCommand("3");

            ButtonGroup group = new ButtonGroup();
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

            GridLayout layout = new GridLayout(3, 1);
            Container pane = getContentPane();
            pane.setLayout(layout);
            FlowLayout flowlayout = new FlowLayout();
            row1.setLayout(flowlayout);

            row3.setLayout(flowlayout);

            pane.add(row1);
            pane.add(row2);
            pane.add(row3);

            setContentPane(pane);

            int result = JOptionPane.showConfirmDialog(null, pane, "Εισαγωγή στοιχειων παίχτη", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String name = field1.getText();
                size = Integer.parseInt(group.getSelection().getActionCommand());
                if (!(field1.getText().equals(""))) {
                    player1.setName(name);
                }

            }
            pane.removeAll();
        });

        //Αναζητηση αρχειου λεξεων
        arxeio.addActionListener(ActionEvent -> {           //Listener για οταν παταει "Αναζητηση αρχειου λεξεων"
            JButton open = new JButton();
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new java.io.File("C:/"));
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = fc.getSelectedFile().getAbsolutePath();
                    System.out.print(path);
                    l1.Gemisma(path);
                } catch (IOException ex) {
                    Logger.getLogger(GFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        //Ρυθμισεις βοηθειων
        helps.addActionListener(ActionEvent -> {          //Listener για οταν παταει "Ρυθμισεις βοηθειων"
            JPanel row0 = new JPanel();
            JPanel row1 = new JPanel();
            JPanel row2 = new JPanel();
            JPanel row3 = new JPanel();
            JPanel row4 = new JPanel();
            JPanel row5 = new JPanel();
            JLabel Default = new JLabel("Ολες οι βοηθειες εχουν προκαθορισμενη τιμη 3");

            JLabel swap = new JLabel("Αλλαγη γραμματων μεταξυ τους", JLabel.RIGHT);
            JTextField field1 = new JTextField(1);
            JLabel mperdema_seira = new JLabel("Μπερδεμα Γραμμης", JLabel.RIGHT);
            JTextField field2 = new JTextField(1);
            JLabel mperdema_stili = new JLabel("Μπερδεμα Στηλης", JLabel.RIGHT);
            JTextField field3 = new JTextField(1);
            JLabel shuffle = new JLabel("Μπερδεμα", JLabel.RIGHT);
            JTextField field4 = new JTextField(1);
            JLabel delete = new JLabel("Διαγραφη γραμμης", JLabel.RIGHT);
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
            GridLayout layout = new GridLayout(6, 1);
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

            int result = JOptionPane.showConfirmDialog(null, pane, "Ρυθμιση βοηθειων", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (!(null == field1.getText())) {
                    swap1 = Integer.parseInt(field1.getText());
                }
                if (!(null == field2.getText())) {
                    swap_lin = Integer.parseInt(field2.getText());
                }
                if (!(null == field3.getText())) {
                    swap_col = Integer.parseInt(field3.getText());
                }
                if (!(null == field4.getText())) {
                    shuffle1 = Integer.parseInt(field4.getText());
                }
                if (!(null == field5.getText())) {
                    del = Integer.parseInt(field5.getText());
                }
            }
            pane.removeAll();
        }
        );

        //Κανονες παιχνιδιου
        kanones.addActionListener(ActionEvent -> {            //Listener για οταν παταει "Κανονες παιχνιδιου"
            String path = null;
            try {
                path = l1.open_file();
            } catch (IOException ex) {
                Logger.getLogger(GFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, path, "Κανόνες Παιχνιδιού", JOptionPane.INFORMATION_MESSAGE);
        }
        );
        //Listener για οταν παταει  "about" 
        about.addActionListener(ActionEvent -> {
            JOptionPane.showMessageDialog(null, "Χαραλαμπος Αντωνιαδης - icsd10011\nΕυκαρπιδης Κωνσταντινος - icsd15051", "Partners", JOptionPane.INFORMATION_MESSAGE);
        }
        );

        JMenuItem eksodos = new JMenuItem("Εξοδος");           //Listener για οταν παταει  "Εξοδος"
        eksodos.addActionListener((ActionEvent) -> {
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

    private Image getScaledImage(Image srcImg, int w, int h) //Resize του κουμπιου ελεγχου της λεξης
    {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

}
