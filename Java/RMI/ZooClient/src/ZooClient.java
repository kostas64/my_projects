
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Pipis
 */
public class ZooClient extends UnicastRemoteObject implements ClientInt {


    public static void main(String[] args) throws RemoteException, MalformedURLException {
     //GUI Start
     CustomGUI c2 = new CustomGUI();
     c2.setVisible(true);

    }

    public ZooClient() throws RemoteException {
        super();
    }

    @Override
    public void sub(ClientInt me) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unsub() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void unsubscribe() throws RemoteException, NotBoundException, MalformedURLException {
        //Αποσύνδεση απο server
        String name = "//localhost/ZooInt";
        ZooInt look_op = (ZooInt) Naming.lookup(name);  
        look_op.unsub();
        System.exit(0);
    }

    public void ConnectToServer() throws RemoteException, NotBoundException, MalformedURLException, IOException {
        //Συνδεση στο server
        String name = "//localhost/ZooInt";
        ClientInt clie = new ZooClient();
        ZooInt look_op = (ZooInt) Naming.lookup(name);    
        look_op.sub(clie);

    }
    
    @Override
    public void getMsg(String s, ImageIcon icon) throws RemoteException {
        //Αν δεν πραγματοποιηθει κινηση δεν μπαινει
        if(!s.isEmpty())
           System.out.println(s);

        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        //Μετατροπη icon σε εικονα
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        //Δημιουργια αρχείο στην τοποθεσία που είναι το project ZooClient
        File outputfile = new File(this.ref+"image.png");
        try {
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(ZooClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
}
