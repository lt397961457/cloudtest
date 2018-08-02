package remote.handler;

import remote.entity.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserHandler extends Remote {
    public String getUserName(int id) throws RemoteException;
    public int getUserCount() throws RemoteException;
    User getUserByname(String userName) throws RemoteException;
}
