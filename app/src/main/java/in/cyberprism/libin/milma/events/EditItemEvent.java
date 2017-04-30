package in.cyberprism.libin.milma.events;

import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 01/05/17.
 */

public class EditItemEvent {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
