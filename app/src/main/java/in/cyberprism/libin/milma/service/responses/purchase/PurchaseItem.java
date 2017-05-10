package in.cyberprism.libin.milma.service.responses.purchase;

/**
 * Created by libin on 10/05/17.
 */

public class PurchaseItem {
    private int itemId;
    private String itemName;
    private String itemImage;
    private String itemQuantity;
    private String itemPrice;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "PurchaseItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                '}';
    }
}
