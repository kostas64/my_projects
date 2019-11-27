/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author P_64
 */
public final class TicTacToe extends JFrame{
    //Read instructions from file
    File file = new File("C:\\Users\\P_64\\Documents\\NetBeansProjects\\TicTacToe\\instructions.txt");
    JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
    JMenuBar bar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenu help = new JMenu("Help");
    JMenu about = new JMenu("About");
    JMenuItem newgame = new JMenuItem("New Game");
    JMenuItem info = new JMenuItem("Info");
    JMenuItem instructions = new JMenuItem("Instructions");
    JPanel panel = new JPanel();
    BufferedReader br = null;
    private int state,counter; //state -> whose player turn is, counter for end game
    private int win ; //win = 1 , player 1 wins. win = 2, player 2 wins
    private boolean flag;
    
    public void newgame() {
    
        menu.add(newgame);
        about.add(info);
        help.add(instructions);
        bar.add(menu);
        bar.add(help);
        bar.add(about);
        setJMenuBar(bar);
        newgame.setEnabled(true);
        newgame.addActionListener((ActionEvent e) -> {
            newgame.setEnabled(false);
            panel.removeAll();
            counter=0;
            state = 0;
            win = 0;
            panel.setLayout(new GridLayout(3,3,5,5));
            bar.setVisible(true);
            for(int i=0; i<9; i++){
                JButton button = new JButton("-");
                button.setName("-");
                button.setPreferredSize(new Dimension(100, 100));
                button.addActionListener((ActionEvent e1) -> {
                    if (state%2==1){
                        button.setName("x");
                        button.setText("x");
                        button.setEnabled(false);
                        win = check(button.getLocation(),"x");
                        finish(win);
                    }else {
                        button.setName("o");
                        button.setText("o");
                        button.setEnabled(false);
                        win = check(button.getLocation(),"o");
                        finish(win);
                    }
                    state++;
                });
                panel.add(button);
            }
            
            container.add(panel);
            getContentPane().add(container);
            panel.validate();
            panel.repaint();
            pack();
        });
       
       info.addActionListener((ActionEvent el) -> {
           JOptionPane.showMessageDialog(this, "Creator : Leuteris Giannarakis");
       });
       
       //Read instructions from file with BufferReader
       instructions.addActionListener((ActionEvent el) -> {
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            }
           String str="";
           String inst = "";
            try {
                while((str = br.readLine()) != null ) {
                    inst = inst + str + '\n';
                }
                 br.close();
                 JOptionPane.showMessageDialog(this, inst);
            } catch (IOException ex) {
                Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            }
       });
 
        
    }
    
    private int check(Point position,String player) {
                   if (player.equals("x")){
                       //--------------------------1st Line---------------------------------------------
                       if (position.x == 0 && position.y == 0){
                           boolean a = panel.getComponent(1).getName().equals("x");
                           if( panel.getComponent(1).getName().equals("x") && panel.getComponent(2).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(3).getName().equals("x") && panel.getComponent(6).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(4).getName().equals("x") && panel.getComponent(8).getName().equals("x")){
                               win = 1;
                           }    
                       }else if(position.x == 105 && position.y == 0){
                           if( panel.getComponent(0).getName().equals("x") && panel.getComponent(2).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(4).getName().equals("x") && panel.getComponent(7).getName().equals("x")){
                               win = 1;
                           } 
                       }else if(position.x == 210 && position.y == 0){
                           if( panel.getComponent(0).getName().equals("x") && panel.getComponent(1).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(5).getName().equals("x") && panel.getComponent(8).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(4).getName().equals("x") && panel.getComponent(6).getName().equals("x")){
                               win = 1;
                           }
                       }
                       //-----------------------------2nd Line-------------------------------------------
                       if (position.x == 0 && position.y == 105){
                           if( panel.getComponent(0).getName().equals("x") && panel.getComponent(6).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(4).getName().equals("x") && panel.getComponent(5).getName().equals("x")){
                               win = 1;
                           }   
                       }else if(position.x == 105 && position.y == 105){
                           if( panel.getComponent(3).equals("x") && panel.getComponent(5).equals("x")){
                               win = 1;
                           }else if(panel.getComponent(1).getName().equals("x") && panel.getComponent(7).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(0).getName().equals("x") && panel.getComponent(8).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(2).getName().equals("x") && panel.getComponent(6).getName().equals("x")){
                               win = 1;
                           }  
                       }else if(position.x == 210 && position.y == 105){
                           if( panel.getComponent(2).getName().equals("x") && panel.getComponent(8).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(3).getName().equals("x") && panel.getComponent(4).getName().equals("x")){
                               win = 1;
                           }
                       }
                       //-----------------------------3rd Line----------------------------------------------
                       if (position.x == 0 && position.y == 210){
                           if( panel.getComponent(0).getName().equals("x") && panel.getComponent(3).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(7).getName().equals("x") && panel.getComponent(8).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(4).getName().equals("x") && panel.getComponent(2).getName().equals("x")){
                               win = 1;
                           }    
                       }else if(position.x == 105 && position.y == 210){
                           if( panel.getComponent(6).getName().equals("x") && panel.getComponent(8).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(1).getName().equals("x") && panel.getComponent(4).getName().equals("x")){
                               win = 1;
                           } 
                       }else if(position.x == 210 && position.y == 210){
                           if( panel.getComponent(2).getName().equals("x") && panel.getComponent(5).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(6).getName().equals("x") && panel.getComponent(7).getName().equals("x")){
                               win = 1;
                           }else if(panel.getComponent(0).getName().equals("x") && panel.getComponent(4).getName().equals("x")){
                               win = 1;
                           }
                       }
                   }else{
                       //--------------------------1st Line---------------------------------------------
                       if (position.x == 0 && position.y == 0){
                           if( panel.getComponent(1).getName().equals("o") && panel.getComponent(2).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(3).getName().equals("o") && panel.getComponent(6).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(4).getName().equals("o") && panel.getComponent(8).getName().equals("o")){
                               win = 2;
                           }    
                       }else if(position.x == 105 && position.y == 0){
                           if( panel.getComponent(0).getName().equals("o") && panel.getComponent(2).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(4).getName().equals("o") && panel.getComponent(7).getName().equals("o")){
                               win = 2;
                           } 
                       }else if(position.x == 210 && position.y == 0){
                           if( panel.getComponent(0).getName().equals("o") && panel.getComponent(1).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(5).getName().equals("o") && panel.getComponent(8).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(4).getName().equals("o") && panel.getComponent(6).getName().equals("o")){
                               win = 2;
                           }
                       }
                       //-----------------------------2nd Line-------------------------------------------
                       if (position.x == 0 && position.y == 105){
                           if( panel.getComponent(0).getName().equals("o") && panel.getComponent(6).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(4).getName().equals("o") && panel.getComponent(5).getName().equals("o")){
                               win = 2;
                           }   
                       }else if(position.x == 105 && position.y == 105){
                           if( panel.getComponent(3).getName().equals("o") && panel.getComponent(5).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(1).getName().equals("o") && panel.getComponent(7).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(0).getName().equals("o") && panel.getComponent(8).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(2).getName().equals("o") && panel.getComponent(6).getName().equals("o")){
                               win = 2;
                           }  
                       }else if(position.x == 210 && position.y == 105){
                           if( panel.getComponent(2).getName().equals("o") && panel.getComponent(8).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(3).getName().equals("o") && panel.getComponent(4).getName().equals("o")){
                               win = 2;
                           }
                       }
                       //-----------------------------3rd Line----------------------------------------------
                       if (position.x == 0 && position.y == 210){
                           if( panel.getComponent(0).getName().equals("o") && panel.getComponent(3).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(7).getName().equals("o") && panel.getComponent(8).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(4).getName().equals("o") && panel.getComponent(2).getName().equals("o")){
                               win = 2;
                           }    
                       }else if(position.x == 105 && position.y == 210){
                           if( panel.getComponent(6).getName().equals("o") && panel.getComponent(8).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(1).getName().equals("o") && panel.getComponent(4).getName().equals("o")){
                               win = 2;
                           } 
                       }else if(position.x == 210 && position.y == 210){
                           if( panel.getComponent(2).getName().equals("o") && panel.getComponent(5).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(6).getName().equals("o") && panel.getComponent(7).getName().equals("o")){
                               win = 2;
                           }else if(panel.getComponent(0).getName().equals("o") && panel.getComponent(4).getName().equals("o")){
                               win = 2;
                           }
                       }
                   }
                   //Check for draw
                   for (int i=0; i<9; i++){
                           if (panel.getComponent(i).getName().equals("o") || panel.getComponent(i).getName().equals("x")){
                               counter++;
                           }
                   }
                   if (counter==9 && win==0){
                       win=3;
                   }else{
                       counter=0;
                   }
                   
                   return win;
    }
    
    public void finish(int win) {
        switch (win) {
            case 1:
                JOptionPane.showMessageDialog(this, "Player 1 wins the game");
                newgame.setEnabled(true);
                break;
            case 2:
                JOptionPane.showMessageDialog(this, "Player 2 wins the game");
                newgame.setEnabled(true);
                break;
            case 3:
                JOptionPane.showMessageDialog(this, "No one won, its a draw");
                newgame.setEnabled(true);
                break;
        }
                       
               }
    
    public int getCounter(){
        return counter;
    }
    
    
    public boolean getFlag(){
        return flag;
    }
    

    public void setWin(int win){
        this.win = win;
    }
    
    public void setFlag(boolean flag){
        this.flag =  flag;
    }
    
    
    public TicTacToe() {
        super("Tic-tac-toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(325,355);
        setLocationRelativeTo(null);
        setVisible(true);
        newgame();
    }
    
  
    public static void main(String[] args) {
      EventQueue.invokeLater(() -> {

            TicTacToe ex = new TicTacToe();
            ex.setVisible(true);
        });
       

    }

    
}
