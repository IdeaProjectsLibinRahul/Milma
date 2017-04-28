package in.cyberprism.libin.milma.service.responses.items;

/**
 * Created by libin on 28/04/17.
 */

public class Item {
    private int itemId;
    private String itemName;
    private String itemImage;

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

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemImage='" + itemImage + '\'' +
                '}';
    }
}
