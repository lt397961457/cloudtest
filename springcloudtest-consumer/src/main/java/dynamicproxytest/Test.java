package dynamicproxytest;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/12/26.
 */
public class Test {
    public static void main(String[] args) {
        OneCls cla = new OneCls();
       OneItf itf = (OneItf)Proxy.newProxyInstance(OneCls.class.getClassLoader(),OneCls.class.getInterfaces(),new MyProxy(cla));
//       itf.show("hahah");
        String result = itf.show2("tttt");
    }
}
