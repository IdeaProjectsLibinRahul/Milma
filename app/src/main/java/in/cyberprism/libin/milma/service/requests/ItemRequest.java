package in.cyberprism.libin.milma.service.requests;

import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.service.requests.base.BaseRequest;

/**
 * Created by libin on 28/04/17.
 */

public class ItemRequest extends BaseRequest {
    private int userId;
    private Constants.GroupBy groupBy;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Constants.GroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(Constants.GroupBy groupBy) {
        this.groupBy = groupBy;
    }
}
