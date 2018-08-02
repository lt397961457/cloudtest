package remote;

import remote.handler.UserHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StartRMIClient {
    /**
     * RMI客户端
     * @param args
     */
    public static void main(String[] args) {
        try {
            /**
             * UserHandler接口  和 User 类在客户端本地必须可用，UserHandler类在客户端本地必须可用，不然无法指定要调用的方法，
             * 而且其全限定名必须与服务器上的对象完全相同，不然抛出如下异常
             *
             * 也就是说 要使用 远程注册的 类，必须本地也要创建一个对应的类，并且包名+类名必须一致，
             * 而且如果是实体类必须要实现序列化接口，并且里面的序列号要和对应远程的实体类里面的序列号一致。
             */
            UserHandler handler = (UserHandler) Naming.lookup("userHandler");
            int  count = handler.getUserCount();
            String name = handler.getUserName(1);
            System.out.println("name: " + name);
            System.out.println("count: " + count);
            System.out.println("user: " + handler.getUserByname("AXZ"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
