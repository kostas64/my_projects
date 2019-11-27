
package hlektr_krypto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;



public class Leitourgies {
    private ArrayList<String> leksiko = new ArrayList<>(); //Λίστα με λεξεις απο το αρχειο
    private ArrayList<String> gamewords = new ArrayList<>();   //Λίστα με τις λέξεις που θα παιξει ο χρηστης
    ArrayList<Character> char_t = new ArrayList<>();    //Χαρακτηρες ταμπλου
     char[] isostixia = new char[24];
      int[] pontoi_anagrama = new int[24];

    Grammata[][] tablo; //ταμπλο παιχνιδιου
    
    public Leitourgies() throws IOException
    {
        Gemisma1(isostixia,pontoi_anagrama);
        Gemisma("lexeis.txt");
    }
   
    public Grammata[][] Gemisma_tablo(int choice,int[] pontoi,char[] isostixia)
    {  
        ArrayList<Grammata> antikeimena = new ArrayList<>(); 
        int i=0,counter=0,size=0,d,nob;
        Collections.shuffle(leksiko); //Μπερδεμα του λεξικου
        switch(choice)              //Επιλογη χρηστη για μεγεθος ταμπλο
        {
            case 1: 
                tablo = new Grammata[5][5];
                size=25;
                break;
            case 2:   
                tablo = new Grammata[8][8];
                size=64;
                break;
            case 3:
                tablo = new Grammata[10][10];
                size=100;
                break;
        } 
       
        while(counter<size)     //Γεμισμα λιστας με λεξεις που θα παιξει ο χρηστης
        {
            gamewords.add(leksiko.get(i));
            counter=counter+leksiko.get(i).length();
            i++;
        }
        counter-=gamewords.get(i-1).length();  //Αφαιρουμε την τελευταια λεξη
        gamewords.remove(i-1);
        d=size-counter;
        
        for (String str: gamewords) //Περασμα χαρακτηρων στο char_t
        {
            for (i=0; i<str.length(); i++){
                char_t.add(str.charAt(i));
              
            }
        }
        
        Random r = new Random(System.currentTimeMillis()); //Δημιουργια μπαλαντερ
        nob=r.nextInt(2);
        
        for (i=0; i<nob; i++)
        {
            char_t.add('?');
            d--;
        }
        
      
        for(i=0; i<d; i++)      //Δημιουργια random υπολλειπομενων γραμματων
        {
            nob=r.nextInt(23);
            char_t.add(isostixia[nob]);
        }
        
        //Collections.shuffle(char_t);
        
        nob=r.nextInt(4);
        d=size-nob;
        
        for(i=0; i<nob; i++) //Δημιουργια κοκκινων γραμματων
        {
            Grammata a = new Kokkino();
            a.setColor("Red");
            antikeimena.add(a);
        }
        
        nob=r.nextInt(3);
        d=d-nob;
        for(i=0; i<nob; i++) //Δημιουργια μπλε γραμματων
        {
            Grammata b = new Mple();
            b.setColor("Blue");
            antikeimena.add(b);
        }
        
        for(i=0; i<d; i++)  //Δημιουργια ασπρων γραμματων
        {
            Grammata c = new Aspro();
            antikeimena.add(c);
        }
        
        for(i=0; i<size; i++)     //Περασμα γραμματων και ποντων στο arraylist(antikeimena)
        {
         antikeimena.get(i).setVal(char_t.get(i));
          int j=0;
          
            while(!char_t.get(i).equals(isostixia[j]))
             {
                 
                 if (char_t.get(i).equals('?')){
                     break;
                 }
                j++;
             }
            antikeimena.get(i).setPoint(pontoi[j]);
        }
        
       Collections.shuffle(antikeimena);
       d=0;
       for (i=0;i<Math.sqrt(size); i++){   //Περασμα arraylist(antikeimena) στο ταμπλο 
           for (int j=0; j<Math.sqrt(size); j++){
               tablo[i][j]=antikeimena.get(d);
               d++;
           }
           
       }     
      return tablo; 
        
    }
    
       
     public Grammata[][] display(int size) //Εμφανιση ταμπλο
     {
        
         for (int i=0;i<Math.sqrt(size); i++){
             System.out.println("\n");
           for (int j=0; j<Math.sqrt(size); j++){
               System.out.print(tablo[i][j].getVal()+ " ");
               
           }
           
       }
       return tablo;
     }
    
    public void Gemisma(String path) throws IOException //Διαβασμα λεξεων απο το αρχειο
    {
    BufferedReader br=null;
    try {int c=0;
         File file = new File(path);
         br = new BufferedReader(new FileReader(file));
         
          String line;
    while ((line = br.readLine()) != null) {
        if (c!=0){
         leksiko.add(line); 

        }
        c++;
        }
       
    } finally {       
             if(br!=null)
             {
               br.close();  
             }
            
         } 
    }
    
    public void swap(int i, int j, int x, int y){ //Αλλαγη 2 γραμματων επιλογης του χρηστη
        Grammata a=new Aspro();
        a=tablo[i][j];
        tablo[i][j]=tablo[x][y];
        tablo[x][y]=a;
    }
    
    public void shuffle(int size){ //shuffle του ταμπλο
        int i,j,d=0;
        ArrayList<Grammata> a= new ArrayList<>();
        for ( i=0; i<Math.sqrt(size);i++){
            for ( j=0; j<Math.sqrt(size);j++){
                a.add(tablo[i][j]);
            }
        }
        Collections.shuffle(a);
        for ( i=0; i<Math.sqrt(size);i++){
            for ( j=0; j<Math.sqrt(size);j++){
               tablo[i][j]=a.get(d);
               d++;
            }
        } 
    }
    
    public void shuffle_line(int size,int line) //Μπερδεμα μια γραμμης που επιθυμει ο χρηστης
    {
        int i,d=0;
        ArrayList<Grammata> a= new ArrayList<>();
        
        for(i=0; i<Math.sqrt(size);i++)
        {
            a.add(tablo[line-1][i]);
        }
        
        Collections.shuffle(a);
        
        for ( i=0; i<Math.sqrt(size);i++)
        {
            tablo[line-1][i]=a.get(d);
            d++;
        }
        
    }
    
    public void shuffle_column(int size,int line)   //Μπερδεμα μια στηλης που επιθυμει ο χρηστης
    {
        int i,d=0;
        ArrayList<Grammata> a= new ArrayList<>();
        
        for(i=0; i<Math.sqrt(size);i++)
        {
            a.add(tablo[i][line-1]);
        }
        
        Collections.shuffle(a);
        
        for ( i=0; i<Math.sqrt(size);i++)
        {
            tablo[i][line-1]=a.get(d);
            d++;
        }  
    }
    
   
    public void delete_line(int size,int line,char[] isostixia,int[] pontoi)        //Διαγραφη μια γραμμης που επιθυμει ο χρηστης
    {
        Random key= new Random(System.currentTimeMillis());
        int r;      
        for(int j=0; j<Math.sqrt(size); j++)
        {
            Grammata aspro = new Aspro();
            r=key.nextInt(23);
            aspro.setVal(isostixia[r]);
            aspro.setPoint(pontoi[r]);
            tablo[line-1][j]=aspro;
            
        }
    }
    
    public void balader(Grammata a,char[] isostixia,int[] pontoi) //Αντικατασταση μπαλαντερ με οποιο γραμμα επιθυμει ο χρηστης
    {
        char gramma;
        int pos=0;
        System.out.println("Δωσε το γραμμα με το οποιο θες να αντικαταστησεις το μπαλαντερ");
        Scanner ans =new Scanner(System.in);
        gramma=ans.nextLine().charAt(0);
        a.setVal(gramma);
        for(int i=0; i<23; i++)
        {
            if(isostixia[i]==gramma)
            {
            pos=i;
            break;
            }  
        }
        a.setPoint(pontoi[pos]);       
    }
    
    
    
     public  int Ypologismos(ArrayList<Grammata> lexi)
    {
        boolean flag=false;
        ArrayList<Grammata> b=new ArrayList<>();
        int sum=0;
        for(Grammata x:lexi)
        {
            if (x instanceof Mple){
                flag=true;
            }
            
                sum+=x.getPoint();
                
            
            System.out.println(x.getColor());
        }
        if (flag)
             sum=2*sum;
        return sum;
    }
     
     public  void Gemisma1(char[] isostixia,int[] pontoi_anagrama) //Αρχικοποιηση γραμματων και ποντων
    {
     isostixia[0]='Α';  
     isostixia[1]='Β';
     isostixia[2]='Γ';
     isostixia[3]='Δ';
     isostixia[4]='Ε';
     isostixia[5]='Ζ';
     isostixia[6]='Η';
     isostixia[7]='Θ';
     isostixia[8]='Ι';
     isostixia[9]='Κ';
     isostixia[10]='Λ';
     isostixia[11]='Μ';
     isostixia[12]='Ν';
     isostixia[13]='Ξ';
     isostixia[14]='Ο';
     isostixia[15]='Π';
     isostixia[16]='Ρ';
     isostixia[17]='Σ';
     isostixia[18]='Τ';
     isostixia[19]='Υ';
     isostixia[20]='Φ';
     isostixia[21]='Χ';
     isostixia[22]='Ψ';
     isostixia[23]='Ω';
     pontoi_anagrama[0]=1;
     pontoi_anagrama[1]=8;
     pontoi_anagrama[2]=4;
     pontoi_anagrama[3]=4;
     pontoi_anagrama[4]=1;
     pontoi_anagrama[5]=8;
     pontoi_anagrama[6]=1;
     pontoi_anagrama[7]=8;
     pontoi_anagrama[8]=1;
     pontoi_anagrama[9]=2;
     pontoi_anagrama[10]=3;
     pontoi_anagrama[11]=3;
     pontoi_anagrama[12]=1;
     pontoi_anagrama[13]=10;
     pontoi_anagrama[14]=1;
     pontoi_anagrama[15]=2;
     pontoi_anagrama[16]=2;
     pontoi_anagrama[17]=1;
     pontoi_anagrama[18]=1;
     pontoi_anagrama[19]=2;
     pontoi_anagrama[20]=8;
     pontoi_anagrama[21]=10;
     pontoi_anagrama[22]=10;
     pontoi_anagrama[23]=3;
    }

}


