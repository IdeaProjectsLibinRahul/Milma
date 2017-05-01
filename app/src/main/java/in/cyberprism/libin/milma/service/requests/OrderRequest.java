package in.cyberprism.libin.milma.service.requests;

import java.util.List;

import in.cyberprism.libin.milma.service.requests.base.BaseRequest;
import in.cyberprism.libin.milma.service.responses.order.OrderItem;

/**
 * Created by libin on 01/05/17.
 */

public class OrderRequest extends BaseRequest {
    private int userId;
    private List<OrderItem> items;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
