//Κωνσταντινος Ευκαρπιδης - icsd15051 - Ασκηση1
import java.util.*;
import java.lang.*;
 
        
public class CompanyCar {


    
    public static void main(String[] args) {
       ArrayList<Car> carCollection = new ArrayList<>(); //Συλλογη αυτοκινητων
       menu(carCollection);
           
    }
    
    public static void menu(ArrayList<Car> carCollection)
                {
       
        int ans=0; //Επιλογη χρηστη
        HashSet<String> Set1 = new HashSet<>(); //Συλλογη μοναδικων μαρκων της 
        
        do
        {
            System.out.println("1)Εισαγωγή νεου αυτοκινήτου στη έκθεση\n" + "2)Αναζήτηση και διαγραφή αυτοκινήτου βάση κωδικού\n3)Εμφάνιση της κάθε μάρκας αυτοκινήτου που είναι διαθέσιμη στην έκθεση\n"
                + "4)Εύρεση αυτοκινήτου με τη μεγαλύτερη και μικρότερη ιπποδύναμη\n5)Εύρεση αυτοκινήτου με τη μεγαλύτερη και μικρότερη ιπποδύναμη ανά μάρκα"
                + "\n6)Εμφάνιση πλήθους αυτοκινήτων ανά μάρκα\n7)Εμφάνιση αυτοκινήτων που έχουν τιμή πώλησης είτε μεγαλύτερη είτε μικρότερη από\n" +
    "μία τιμή που επιθυμεί ο χρήστης\n8)Έξοδος");
            Scanner b =new Scanner(System.in);
            ans = b.nextInt();
            switch(ans) //Επιλογη χρηστη απο μενου
            {
               case 1: 
                 eisagogi(carCollection,Set1);
                 break;
                case 2:
                 diagrafi(carCollection,Set1);
                   break;
                case 3: 
                 emfanisi_mon(carCollection,Set1);
                   break;
                case 4:
                 displ_max_min_hp(carCollection);
                   break;
                case 5:
                 displ_max_min_hp_per_com(carCollection,Set1);
                 break;
                case 6:
                    plithos(carCollection,Set1);
                    break;
                case 7:
                    sugkrisi(carCollection);
                    break;
          }     
        }while(ans!=8);            
}
    
    public static void eisagogi(ArrayList<Car> carCollection,HashSet<String> Set1)
    {
        float price;
        int code,hp,cc;
        String ComName,Model,Color;
        Scanner b =new Scanner(System.in);
                 Random a = new Random(System.currentTimeMillis());
                     do
                            {
                          code=a.nextInt(999); //3-ψηφιος μοναδικος κωδικος 
                            }
                     while (code<100);       
                    Car c1=new Car(code);
                    carCollection.add(c1);                   
                    System.out.println("Δωσε μαρκα");
                    ComName=b.nextLine();
                    c1.setComName(ComName);                       
                    System.out.println("Δωσε μοντελο");
                    Model=b.nextLine();
                    c1.setModel(Model);
                    System.out.println("Δωσε ιππους");
                    hp=b.nextInt();
                    c1.setHP(hp);
                    b.nextLine();
                    System.out.println("Δωσε αριθμο κυβικων");
                    cc=b.nextInt();
                    c1.setCc(cc);
                    b.nextLine();
                    System.out.println("Δωσε τιμη");
                    price=b.nextFloat();
                    c1.setPrice(price);
                    b.nextLine();
                    System.out.println("Δωσε χρωμα");
                    Color=b.nextLine();
                    c1.setColor(Color);
                    Set1.add(ComName.toLowerCase());   //Τα κανω ολα μικρα για να μην εχω προβλημα πχ με Toyota - toyota.            
    }
    
    public static void diagrafi(ArrayList<Car> carCollection,HashSet<String> Set1)
    {
        int code,cou=0;
        boolean flag=false;  
        String marka=" ";
        Scanner b =new Scanner(System.in);
        Car car ;        
                    System.out.println("Δωσε κωδικο αυτοκινητου");
                    code=b.nextInt();
                    for(int i=0; i<carCollection.size(); i++)
                        {
                            car=carCollection.get(i); //Ψαχνω στην συλλογη να βρω τον κωδικο που εδωσε ο χρηστης
                        if(car.getCode()==code) //Αν τον βρει κανω το flag true τον μετρητη +1 και κραταω την μαρκα ωστε αν ειναι το μοναδικο στοιχειο να μην σβηστει απο το Set1.
                            {
                               System.out.println("Το αυτοκινητο με κωδικο" + car.getCode() + "βρεθηκε και διαγραφηκε");
                               marka=car.getComName();
                               carCollection.remove(i);                              
                               cou++;
                               flag=true;
                               break;
                            }                            
                        }
                     for(int i=0; i<carCollection.size(); i++)
                         {
                         car=carCollection.get(i);
                          if(car.getComName()==marka){
                                cou++;
                         }                  
                    }
                     if (cou>0 && flag)
                    Set1.remove(marka);
                    if(!flag)
                        System.out.println("Δεν βρεθηκε αυτοκινητο με αυτον τον κωδικο στην εκθεση");
    }
    
    
    public static void emfanisi_mon(ArrayList<Car> carCollection,HashSet<String> Set1) //Εμφάνιση της κάθε μάρκας αυτοκινήτου που είναι διαθέσιμη στην έκθεση
    {
        for(String str: Set1)
        {
                    System.out.println(str);
        }
    }
    
    public static void displ_max_min_hp(ArrayList<Car> carCollection) //Εύρεση αυτοκινήτου με τη μεγαλύτερη και μικρότερη ιπποδύναμη
    {
        Car car;    
        int max=0,min=5000;
        int max_pos=0,min_pos=0; //Θεση ωστε να μπορω να τα εμφανισω μετα.
        
                 for(int i=0; i<carCollection.size(); i++) //Τρεχουμε τον πινακα για να βρουμε max,min.
                 {
                    car=carCollection.get(i);
                    if (car.getHP()>max)
                    {
                        max=car.getHP();
                        max_pos=i;
                    }
                        
                    if (car.getHP()<min)
                    {
                        min=car.getHP();
                        min_pos=i;
                    }
                 }
                 System.out.println("to amaksi me ti mokroteri ippodunami einai to "+ carCollection.get(min_pos).getComName()+" "+carCollection.get(min_pos).getModel() + " me "+carCollection.get(min_pos).getHP()+" aloga" );
                 System.out.println("to amaksi me ti megaluteri ippodunami einai to "+ carCollection.get(max_pos).getComName()+" "+carCollection.get(max_pos).getModel() + " me "+carCollection.get(max_pos).getHP()+" aloga" );
    }
    public static void displ_max_min_hp_per_com(ArrayList<Car> carCollection,HashSet<String> Set1) //Εύρεση αυτοκινήτου με τη μεγαλύτερη και μικρότερη ιπποδύναμη ανά μάρκα
    {
        Car car;      
        int[][] array_stuff = new int[Set1.size()][4]; //Ενας πινακας με 4 στηλες και Set1.size() γραμμες. 1η στηλη max - 3η θεση max_pos,2η στηλη min - 4η στηλη min_pos
               
            for(int j=0; j<4; j++)
                {
                    for(int i=0; i<Set1.size(); i++) //Αρχικοποιηση πινακα
                    {
                        if (i!=1)
                        {
                          array_stuff[i][j]=0;  
                        }
                        else
                        {
                            array_stuff[i][j]=10000;
                        }  
                    }
                }
                for(int i=0; i<carCollection.size(); i++) //Ευρεση max,min,max_pos,min_pos
                {
                    car=carCollection.get(i);
                    int cou=0;
                    for(String string: Set1)
                    {                  
                        if(string.equals(car.getComName()))
                        {
                            break;
                        }    
                        cou++;
                    }
                     if (car.getHP()>array_stuff[cou][0])
                     {
                        array_stuff[cou][0]=car.getHP();
                        array_stuff[cou][2]=i;                        
                     }
                        
                    if (car.getHP()<array_stuff[cou][1]){
                        array_stuff[cou][1]=car.getHP();
                        array_stuff[cou][3]=i;                        
                    }
                  }                
                 for (int i=0; i<Set1.size(); i++)
                    {               
                 System.out.println("Το " + carCollection.get(array_stuff[i][3]).getComName() + " με τη μικροτερη ιπποδυναμη ειναι το " + carCollection.get(array_stuff[i][3]).getModel() + " με " + carCollection.get(array_stuff[i][3]).getHP() + " αλογα");
                 System.out.println("Το " + carCollection.get(array_stuff[i][2]).getComName() + " με τη μεγαλυτερη ιπποδυναμη ειναι το  " + carCollection.get(array_stuff[i][2]).getModel() + " με " + carCollection.get(array_stuff[i][2]).getHP() + " αλογα");
                     }
    }
     public static void plithos(ArrayList<Car> carCollection,HashSet<String> Set1)
     {
         Car car;
         int j=0,cou; //j index σε Enhanced for ωστε να τρεχουμε ταυτοχρονα και το Set1 και τον πινακα τον μετρητων
         int[] array_cou = new int[Set1.size()]; //Πινακας μετρητων.
         
                for(int i=0; i<Set1.size(); i++) //Αρχικοποιηση πινακα
                {
                    array_cou[i]=0;
                }
                for (int i=0; i<carCollection.size(); i++) //Παιρνει την μαρκα απο το Set1 και ελεγχει ποσα τετοια υπαρχουν στο πινακα 
                {
                    cou=0;
                    car=carCollection.get(i);       
                    for(String string: Set1)
                    {                       
                        if (car.getComName().equals(string))
                        {
                            array_cou[cou]++;
                            break;
                        }
                        cou++;
                    } 
                }                 
                for(String string: Set1)
                {
                    System.out.println("Τα "+ string +" ειναι "+ array_cou[j]);
                    j++;
                }              
     }
     
     public static void sugkrisi(ArrayList<Car> carCollection) //Εμφάνιση αυτοκινήτων που έχουν τιμή πώλησης είτε μεγαλύτερη είτε μικρότερη από μία τιμή που επιθυμεί ο χρήστης
     {
         Car car;
         String oper;
         float price;
         Scanner a = new Scanner(System.in);
                
                System.out.println("Δωσε '<' η '>' για συγκριση"); //Δινει ο χρηστης < ή >
                oper=a.nextLine();   
                System.out.println("Δωσε τιμη για συγκριση");
                price=a.nextFloat();
                if ("<".equals(oper)) // Αν < τρεχει την Collection και ψαχνει αμαξια με μικροτερη τιμη απο αυτη
                {
                for (int i=0; i<carCollection.size(); i++)
                    {
                    car=carCollection.get(i);
                    if (car.getPrice()<price)
                            {
                        System.out.println("Το"+ car.getComName() + " " + car.getModel()+" με τιμη "+ car.getPrice());
                            }
                    }
                }
                else // Αν > τρεχει την Collection και ψαχνει αμαξια με μικροτερη τιμη απο αυτη
                { 
                    for (int i=0; i<carCollection.size(); i++)
                    {
                    car=carCollection.get(i);
                    if (car.getPrice()>price)
                         {
                        System.out.println("Το"+ car.getComName() + " " + car.getModel()+" με τιμη "+ car.getPrice());
                         }                   
                    }                    
                }
     }
}
