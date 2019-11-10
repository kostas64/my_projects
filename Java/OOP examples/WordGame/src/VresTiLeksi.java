//Κωνσταντινος Ευκαρπιδης - icsd15051 - Ασκηση3
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class VresTiLeksi {

  static  int counter=1; //Μετρητης για γυρο παιχνιδιου
  
    public static void main(String[] args) throws UnsupportedEncodingException
    {
      game player;
      boolean apotelesma=true;
      ArrayList<Lekseis> oles = new ArrayList<>(); //Λιστα με ολες τις λεξεις
      ArrayList<Lekseis> mono_paix = new ArrayList<>(); //Λιστα με τις λεξεις που θα παιξει ο χρηστης
      Dim_leks(oles);
      player=Ksekinima();
      
      while((oles.size()>=3) && (apotelesma)) //Του λεω οτι μπορει να παιζει οσο υπαρχουν λεξεις στην λιστα με ολες τις λεξεις και αν το εχει κερδισει
      {
           Gemisma(oles,mono_paix);
           apotelesma=Sunexeia(oles,mono_paix,player);
      }
      if((oles.size()<3) && (apotelesma)) //Τελιωνει το παιχνιδι γιατι δεν υπαρχουν αλλες λεξεις
      {
          System.out.println("Δεν υπαρχουν αλλες λεξεις για να παιξετε");
      }
    }
    
    public static void Dim_leks(ArrayList<Lekseis> a) //Δημιουργια λιστας λεξεων
    {
       a.add(new Lekseis("PODILATO","ΟΧΗΜΑ ΜΕ 2 ΡΟΔΕΣ,ΟΙΚΟΝΟΜΙΚΟ"));
       a.add(new Lekseis("MPLOUZA","ΡΟΥΧΟ ΠΟΥ ΦΟΡΑΜΕ ΠΑΝΤΑ,ΜΠΟΡΕΙ ΝΑ ΕΙΝΑΙ ΚΟΝΤΟ Η ΜΑΚΡΥ."));
       a.add(new Lekseis("POTIRI","ΤΟ ΧΡΗΣΙΜΟΠΟΙΟΥΜΕ ΓΙΑ ΝΑ ΕΧΟΥΜΕ ΣΥΝΗΘΩΣ ΜΕΣΑ ΥΓΡΑ,ΕΧΕΙ ΠΟΛΛΑ ΕΙΔΗ ΓΙΑ ΔΙΑΦΟΡΕΤΙΚΑ ΥΓΡΑ."));
       a.add(new Lekseis("STILO","ΤΟ ΧΡΗΣΙΜΟΠΟΙΟΥΜΕ ΓΙΑ ΝΑ ΓΡΑΨΟΥΜΕ,ΕΧΕΙ ΑΚΡΙΒΑ ΚΑΙ ΦΘΗΝΑ"));
       a.add(new Lekseis("GIALIA","ΑΞΕΣΟΥΑΡ ΠΟΥ ΦΟΡΑΜΕ ΣΤΟ ΚΕΦΑΛΙ,ΜΑΣ ΠΡΟΣΤΑΤΕΥΕΙ ΑΠΟ ΤΟΝ ΗΛΙΟ"));
       a.add(new Lekseis("YPOLOGISTIS","ΤΟ ΧΡΗΣΙΜΟΠΟΙΟΥΜΕ ΣΤΗΝ ΔΟΥΛΕΙΑ ΜΑΣ,ΥΠΑΡΧΕΙ ΚΑΙ ΦΟΡΗΤΟ"));     
    }
    
  
    
    public static void Gemisma(ArrayList<Lekseis> all,ArrayList<Lekseis> only) //Γεμισμα τηε λιστας των λεξεων που θα παιξει ο χρηστης τυχαια
    {
       int key;
       Random1 r= new Random1(System.currentTimeMillis());
         for(int i=0; i<3; i++)
              {
        key=r.nextInt(all.size()-i-1);
        only.add(all.get(key));  
        all.remove(key);       
              } 
    }
    public static game Ksekinima() //Ξεκινημα παιχνιδιου
    {
       String playerName;
       int level;      
       Scanner a =new Scanner(System.in);
       System.out.println("ΚΑΛΩΣΗΡΘΑΤΕ ΣΤΟ ΠΑΙΧΝΙΔΙ «ΒΡΕΣ ΤΙΣ ΛΕΞΕΙΣ»");
       System.out.print("Εισάγετε το όνομα σας:");
       playerName=a.nextLine();
       level=3;
       
       while((level!=1) && (level!=2)) //Ελεγχος εγκυροτητας
             {
           System.out.print("Επιλέξτε επίπεδο δυσκολίας (1 – Εύκολο, 2 – Δύσκολο):");
           level=a.nextInt();  
             }     
       
       game g = new game(level,playerName);
       return g;
    }
    public static boolean Sunexeia(ArrayList<Lekseis> all,ArrayList<Lekseis> only,game g) throws UnsupportedEncodingException
    {
        ArrayList<Character> xaraktires =new ArrayList<>(); //Λιστα με τα ολα τα γραμματα μεμονομενα
        
        int lathoi=0;
        int answer; //Επιλογη λεξεις απο τον χρηστη
        String leksi;
        int apa; //Επιλογη χρηστη για το αν θα ξαναπαιξει
        if(g.getLevel()==1) //Επιπεδο 1
             {
                 System.out.println(counter + "ος ΓΥΡΟΣ ΠΑΙΧΝΙΔΙΟΥ");
        for(int i=0; i<3; i++)
                {
                    System.out.print(only.get(i).getPerigrafi() + "\t");
                   System.out.print(only.get(i).getLeksi().charAt(0)); //Εμφανιση 1ου γραμματος
                     for(int j=1; j<only.get(i).getLeksi().length()-1; j++)
                        {
                         xaraktires.add(only.get(i).getLeksi().charAt(j)); //Κατω παυλες
                          System.out.print(" _ ");
                        }
                      System.out.println(only.get(i).getLeksi().charAt(only.get(i).getLeksi().length()-1)); //Εμφανιση τελευταιου γραμματος
                }
            Collections.shuffle(xaraktires); //Μπερδεμα γραμματων
            while(lathoi<3 && (!xaraktires.isEmpty()))
            {          
                for(int i=0; i<xaraktires.size(); i++) //Εμφανιση διαθεσιμων γραμματων για ταιριασμα
                        {
                    System.out.print(xaraktires.get(i));
                    System.out.print(" ");
                         }  
                
             Scanner ans = new Scanner(System.in);
             System.out.print("\nΕπιλέξτε λέξη που θέλετε να μαντέψετε : ");
             answer=ans.nextInt();
             
                while((answer!=1) && (answer!=2) && (answer!=3))
                         {
                     System.out.print("\nΕπιλέξτε λέξη που θέλετε να μαντέψετε : ");
                     answer=ans.nextInt();
                         }
                
             ans.nextLine();
             System.out.print("Εισάγετε την λεξη : ");
             leksi=ans.nextLine();
             
             if(leksi.equals(only.get(answer-1).getLeksi()))
                     {
                System.out.println("Μπράβο. Μαντέψατε σωστά!!!");
              
                    for(int j=1; j<only.get(answer-1).getLeksi().length()-1; j++) //Αφαιρω τους χαρακτηρες της λεξης που βρηκε
                               {
                     xaraktires.remove((Character)only.get(answer-1).getLeksi().charAt(j)); 
                               }                   
                     }
             else
                 {
                 lathoi++;
                 System.out.println("Λάθος.Εχετε ακόμα " + (3-lathoi) + " προσπαθιες");                
                 } 
            }
         }
        else //Επιπεδο 2
        {
             System.out.println(counter + "ος ΓΥΡΟΣ ΠΑΙΧΝΙΔΙΟΥ");
        for(int i=0; i<3; i++)
                {
                    System.out.print(only.get(i).getPerigrafi() + "\t"); //Εμφανιση μονο περιγραφης και διαθεσιμων γραμματων για ταιριασμα
                   
                     for(int j=0; j<only.get(i).getLeksi().length(); j++)
                     {
                         xaraktires.add(only.get(i).getLeksi().charAt(j));
                          System.out.print(" _ ");
                     }
                     System.out.print("\n");
                     
                }
            Collections.shuffle(xaraktires); 
            while(lathoi<3 && (!xaraktires.isEmpty()))
             {
            
                for(int i=0; i<xaraktires.size(); i++)
                        {
                    System.out.print(xaraktires.get(i));
                    System.out.print(" ");
                         }  
                
             Scanner ans = new Scanner(System.in);
             System.out.print("\nΕπιλέξτε λέξη που θέλετε να μαντέψετε : ");
            answer=ans.nextInt();
            
                while((answer!=1) && (answer!=2) && (answer!=3))
                         {
                     System.out.print("\nΕπιλέξτε λέξη που θέλετε να μαντέψετε : ");
                     answer=ans.nextInt();
                         }
                
             ans.nextLine();
             System.out.print("Εισάγετε την λεξη : ");
             leksi=ans.nextLine();
             
             if(leksi.equals(only.get(answer-1).getLeksi()))
                     {
              System.out.println("Μπράβο. Μαντέψατε σωστά!!!");
              
                    for(int j=0; j<only.get(answer-1).getLeksi().length(); j++)
                               {
                     xaraktires.remove((Character)only.get(answer-1).getLeksi().charAt(j)); 
                               }                   
                     }
             else
                 {
                 lathoi++;
                 System.out.println("Λάθος.Εχετε ακόμα " + (3-lathoi) + " προσπαθιες");                
                 }               
             }
        }
        
        counter++;
        for(int i=0; i<3; i++)
         {      
             only.remove(0); //Κανω remove καθε φορα το 0ικο στοιχειο γιατι ο πινακας κανει shift αριστερα τα στοιχεια. 
         }
               
       if(xaraktires.isEmpty()) 
       {
          System.out.println("Μπράβο,κερδισατε! \n Θελετε να ξαναπαιξετε? <1> για ναι και <2> για οχι");
          Scanner b = new Scanner(System.in);
          apa=b.nextInt();
          
          if (apa==1)
             {
              return true;
             }          
          return false;
       }
       else
       {
         System.out.println("Χασατε");
         return false;
       }
    }   
}
