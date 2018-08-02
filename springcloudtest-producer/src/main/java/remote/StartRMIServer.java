package remote;

import remote.handler.UserHandler;
import remote.handler.UserHandlerImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class StartRMIServer {
    /**
     * 在启动服务器的时候，实际上需要运行两个服务器：

     一个是远程对象本身；
     一个是允许客户端下载远程对象引用的注册表；
     *
     * 1、先启动rmiregistry.exe(命令在JAVA 安装目录的bin目录下)；
     * 由于远程对象需要与注册表对话，所以必须首先启动注册表程序。
     * 当注册表程序没有启动的时候，如果强行启动远程对象服务器时，会抛出Connect Refused异常。
     * rmiregistry.exe默认情况下是监听1099端口，如果已经该端口已经被使用了，可以通过命令rmiregistry 1020  指定1020端口；
     * 可以通过start rmiregistry命令在后台运行
     *
     * 注意：需要在你的程序的classpath下运行rmiregistry.exe
     * 如果是使用maven管理工程，则在target/classes目录中启动该程序
     *
     * 2、行完注册表程序后，就可以运行远程对象所在的服务器，以便接受客户端的连接。
     *
     * @param args
     */
    public static void main(String[] args) {
        UserHandler userHandler = null;

        try {
            userHandler = new UserHandlerImpl();
            Naming.bind("userHandler",userHandler);
            System.out.println("userHandler:"+userHandler.hashCode());
            System.out.println("RMI SERVER is reading ....");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
