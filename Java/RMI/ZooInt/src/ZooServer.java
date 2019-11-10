
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ZooServer extends UnicastRemoteObject implements ZooInt {

    private static List<BufferedImage> images = new ArrayList<>();
    private int move;
    private static ZooServer zs;    
    private static boolean b;
    File img1;
    File img2;
    File img3;
    File img4;
    BufferedImage image1;
    BufferedImage image2;
    BufferedImage image3;
    BufferedImage image4;
    static ImageIcon img;

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        try {
            zs = new ZooServer();
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            String name = "//localhost/ZooInt";
            Naming.rebind(name, zs);

            System.out.println(zs.getClass().getName());
            System.out.println("Server up and running....");
            zs.loadpic();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    public ZooServer() throws RemoteException {
        super();
    }

    
    public boolean checkMove() throws RemoteException {
        Random r = new Random();
        move = r.nextInt(5); //Θεωρω οτι για 0 δεν ανιχνευει 
        //και απο 1-4 ειναι οι 4 κινησεις

        if (move == 0) 
            return false;

        return true;
    }

    
    public String createLog(boolean b) {
        String time;
        if (b) {
            String amORpm;
            Calendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int seconds = calendar.get(Calendar.SECOND);

            if (calendar.get(Calendar.AM_PM) == 0) {
                amORpm = "AM";
            } else {
                amORpm = "PM";
            }

            time = hour + ":" + minute + ":" + seconds + " " + amORpm;

        } else {
            time = "";
        }
        return time;
    }

    
    public void loadpic() throws IOException  {

        img1 = new File("C:\\Users\\P_64\\Desktop\\ZooInt\\src\\frames\\tile000.png");
        img2 = new File("C:\\Users\\P_64\\Desktop\\ZooInt\\src\\frames\\tile001.png");
        img3 = new File("C:\\Users\\P_64\\Desktop\\ZooInt\\src\\frames\\tile002.png");
        img4 = new File("C:\\Users\\P_64\\Desktop\\ZooInt\\src\\frames\\tile003.png");

        image1 = ImageIO.read(img1);
        image2 = ImageIO.read(img2);
        image3 = ImageIO.read(img3);
        image4 = ImageIO.read(img4);

        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);

    }

    public static ImageIcon whichImage(int move) {
        //Επίλεξα ImageIcon γιατι το Image δεν ειναι serializable
        //με αποτελεσμα να μην με αφηνε να στειλω την εικονα
        switch (move) {
            case 1:
                return new ImageIcon(images.get(0));
            case 2:
                return new ImageIcon(images.get(1));
            case 3:
                return new ImageIcon(images.get(2));
            case 4:
                return new ImageIcon(images.get(3));
            default:
                return null;
        }
    }

    @Override
    public void sub(ClientInt client) throws RemoteException, IOException, MalformedURLException {
        System.out.println("Guard subscribed");

        //Κραταμε την ωρα που εκανε εγγραφη στην βαρδια
        //για να την συγκρινουμε με την τωρινη για απεγγραφη
        String amORpm;
        Calendar calendar = new GregorianCalendar();
        int hour_subbed = calendar.get(Calendar.HOUR);
        int minute_subbed = calendar.get(Calendar.MINUTE);
        int seconds_subbed = calendar.get(Calendar.SECOND);
        if (calendar.get(Calendar.AM_PM) == 0) {
            amORpm = "AM";
        } else {
            amORpm = "PM";
        }

        while (true) {
            Thread.currentThread();
            try {

                b = zs.checkMove();
                //Πραγματοποιηση κίνησης
                if (b) {
                    //Στελνει την ωρα και την εικονα
                    client.getMsg(createLog(b), whichImage(move));
                    //Μεταφερεται στον φακελο ZooClient 
                    //μια για καθε client ,και ενημερωνεται εκει
                    System.out.println("Imaged Moved");
                }
                //Χρονος μεταξυ συμβαντων 5sec
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ZooServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (checkFinish(amORpm, hour_subbed, minute_subbed, seconds_subbed)) {
                break;
            }

        }
    }

    @Override
    public void unsub() throws RemoteException {
        System.out.println("Guard unsubscribed");
    }

    public boolean checkFinish(String ampm, int hour_subbed, int minute_subbed, int seconds_subbed) {
        //Ευρεση ώρας ,ωστε να συγκρινουμε και να δουμε
        //αν μπορουμε να του επιτρεψουμε να απεγγραφει
        String amORpm;
        Calendar calendar = new GregorianCalendar();
        //int hour = calendar.get(Calendar.HOUR);
        //int minute = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        if (calendar.get(Calendar.AM_PM) == 0) {
            amORpm = "AM";
        } else {
            amORpm = "PM";
        }

        //Εστω οτι περναει η ωρα και ληγει η βαρδια μετά απο 15 sec για να μπορεις 
        //να πατησεις disconnect απο την πλατφορμα
        //Επιστρεφωντας true στην ουσια κλεινει η sub()
        //και ενεργοποιείτε το κουμπι disconnect του GUI
        if (amORpm.equals(ampm) && abs(seconds - seconds_subbed) > 15) 
            return true;
        
        return false;
    }

}
