package dynamicproxytest;

/**
 * Created by Administrator on 2017/12/26.
 */
public class OneCls implements OneItf {

    @Override
    public void show(String msg) {
        System.out.println("OneCls--------->"+msg);
    }

    @Override
    public String show2(String msg) {
        System.out.println("OneCls----show2----->"+msg);
        return msg+2;
    }
}
