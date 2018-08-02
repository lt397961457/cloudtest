package remote.handler;

import remote.entity.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserHandlerImpl extends UnicastRemoteObject implements UserHandler {

    public UserHandlerImpl() throws RemoteException {
        super();
    }

    @Override
    public String getUserName(int id) throws RemoteException {
        return "litao1";
    }

    @Override
    public int getUserCount() throws RemoteException {
        return 10;
    }

    @Override
    public User getUserByname(String userName) throws RemoteException {
        return new User(1,userName);
    }
}
