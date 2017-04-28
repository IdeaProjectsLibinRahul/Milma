package in.cyberprism.libin.milma.configurations;

import in.cyberprism.libin.milma.models.User;

/**
 * Created by libin on 28/04/17.
 */

public class Config {
    private static final Config ourInstance = new Config();
    private User user;

    private Config() {
    }

    public static Config getInstance() {
        return ourInstance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
