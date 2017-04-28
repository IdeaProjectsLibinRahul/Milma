package in.cyberprism.libin.milma.service.responses.base;

import in.cyberprism.libin.milma.configurations.Constants;

/**
 * Created by libin on 28/04/17.
 */

public abstract class BaseResponse {
    private String message;
    private Constants.Status status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Constants.Status getStatus() {
        return status;
    }

    public void setStatus(Constants.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
