package in.cyberprism.libin.milma.events;

import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 28/02/17.
 */

public class ItemSelectEvent {
    private String key;
    private int count;
    private Product product;
    private boolean needDialog;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean needDialog() {
        return needDialog;
    }

    public void setNeedDialog(boolean needDialog) {
        this.needDialog = needDialog;
    }
}
