package dynamicproxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/12/26.
 */
public class MyProxy implements InvocationHandler {

    private Object obj;

    public MyProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("proxy----proxy--->"+proxy.getClass().getName());
        System.out.println("proxy----method--->"+method.getName());
       for(int i=0;i<args.length;i++){
           System.out.println("proxy----args["+i+"]--->"+args[i]);
       }
        Object result = method.invoke(obj,args);

        return result.toString() + "kkkk";
    }
}
