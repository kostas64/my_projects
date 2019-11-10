
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Pipis
 */
public class Server {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException, IOException {
        String name;
        Socket socket;
        ServerSocket sock = new ServerSocket(8080);  //Γινεται το connection
        System.out.println("Waiting for connection...");
        socket = sock.accept();
        System.out.println("Connection established...");
        
        BufferedWriter s_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));; //Δημιουργία Ροής για αποστολή IP,όνομα αρχείου
        BufferedReader s_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
        System.out.print("User with ip : " + s_br.readLine() + " is sending a file with name : ");
        name = s_br.readLine();
        System.out.println(name);
        
        DataInputStream dis = new DataInputStream(socket.getInputStream());;
        FileOutputStream fos = new FileOutputStream(name);      //Αποθηκευση στο φακελο του project
        byte[] buffer = new byte[4096];
        int counter = 0;
        int read;
        int totalRead = 0;

        while ((read = dis.read(buffer)) > 0) {  //Διαβασμα απο buffer και αποθηκευση αρχειου
            if (counter == 0) { //Εμφανιση μυνηματος αποστολης 1 φορα
                System.out.println("Sending...");
                counter++;
            }
            totalRead += read; //Ενημέρωση μεταβλητής ελέγχου εναπομείναντων δεδομένων 
            fos.write(buffer, 0, read);
            fos.flush();
        }
        System.out.println("File sent!");

        s_bw.close();       //Κλεισιμο ροων
        s_br.close();
        dis.close();
        fos.close();

        Thread.sleep(1000000);

    }

}
