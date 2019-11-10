/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;

/**
 *
 * @author Pipis
 */
public interface ClientInt extends Remote {

    public void sub(ClientInt me ) throws RemoteException;

    public void unsub() throws RemoteException;

    public void getMsg(String s,ImageIcon pic) throws RemoteException;

}
