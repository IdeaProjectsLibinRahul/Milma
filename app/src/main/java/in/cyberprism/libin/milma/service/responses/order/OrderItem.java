package in.cyberprism.libin.milma.service.responses.order;

/**
 * Created by libin on 01/05/17.
 */

public class OrderItem {
    private int itemId;
    private int quantity;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
