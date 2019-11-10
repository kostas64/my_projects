
import java.io.IOException;
import java.rmi.*;

public interface ZooInt extends Remote {

    public void sub(ClientInt client) throws RemoteException, IOException;

    public void unsub() throws RemoteException;

}
