package in.cyberprism.libin.milma.service.requests;

import in.cyberprism.libin.milma.service.requests.base.BaseRequest;

/**
 * Created by libin on 28/04/17.
 */

public class LoginRequest extends BaseRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
