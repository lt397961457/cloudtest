package remote.entity;


import java.io.Serializable;

public class User implements Serializable{
    /**
     * 1、如果没有该字段，则默认该类会随机生成一个整数，且在客户端和服务器生成的整数不相同，则会抛出异常
     * 2、而且在服务器和客户端这个字段必须保持一致才能进行反序列化，如果两端都有该字段，但是数据不一致，则会抛出异常
     * 3、这个类在服务器和客户端都必须可用
     */
    private static final long serialVersionUID = -7744963497147818328L;
    private int id;
    private String userName;

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
