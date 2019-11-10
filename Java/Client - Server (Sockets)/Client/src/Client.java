
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Pipis
 */
public class Client {

    private JFileChooser j;
    private int check;
    private Socket socket;

    public File chooseFile() {
        BufferedWriter c_bw = null;
        j = new JFileChooser();
        File f = new File("C://"); //Φακελος που ανοιγει το file chooser
        j.setCurrentDirectory(f);
        check = j.showOpenDialog(null);
        if (check == JFileChooser.APPROVE_OPTION) {
            System.out.println("File choosed");
        } else {
            JOptionPane.showMessageDialog(null, "You didnt choose nothing");
        }

        return j.getSelectedFile(); //Επιστρεφουμε το path ωστε να περασουμε σαν παραμετρο στην συναρτηση sendFile
    }

    public void sendFile(File path) throws IOException {

        BufferedWriter c_bw;
        BufferedReader c_br;
        String ipAdd;
        InetAddress host;
        String fileName = path.getName();
        String size = Long.toString(path.getTotalSpace());

        try {
            socket = new Socket("localhost", 8080);
            host = InetAddress.getLocalHost();
            c_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            c_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ipAdd = host.getHostAddress();
            c_bw.write(ipAdd); // Περασμα πληροφοιρων πριν την αποστολη του αρχειου
            c_bw.newLine();
            c_bw.write(fileName);
            c_bw.newLine();
            c_bw.flush();

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); 
            FileInputStream fis = new FileInputStream(path); //Δήλωση ροών και buffer
            byte[] buffer = new byte[4096]; 

            while (fis.read(buffer) > 0 ) { //Αποστολή αρχείου
                dos.write(buffer);
            }

            c_bw.close();   //Κλεισιμο ροων
            c_br.close();
            fis.close();
            dos.close();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
