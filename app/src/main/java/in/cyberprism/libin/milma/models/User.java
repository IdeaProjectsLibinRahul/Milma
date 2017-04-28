package in.cyberprism.libin.milma.models;

import in.cyberprism.libin.milma.configurations.Constants;

/**
 * Created by libin on 28/04/17.
 */

public class User {
    private String name;
    private int userId;
    private Constants.Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Constants.Type getType() {
        return type;
    }

    public void setType(Constants.Type type) {
        this.type = type;
    }
}
