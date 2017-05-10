package in.cyberprism.libin.milma.service.requests;

import in.cyberprism.libin.milma.service.requests.base.BaseRequest;

/**
 * Created by libin on 09/05/17.
 */

public class HistoryRequest extends BaseRequest {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
