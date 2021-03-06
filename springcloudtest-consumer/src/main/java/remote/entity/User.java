package remote.entity;


import java.io.Serializable;

public class User implements Serializable{
    /**
     * 客户端的引用类型的serialVersionUID字段要与服务器端的对象保持一致
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
