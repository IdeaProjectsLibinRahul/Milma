package in.cyberprism.libin.milma.service.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.service.mappers.base.ResponseMapper;
import in.cyberprism.libin.milma.service.responses.ItemsResponse;
import in.cyberprism.libin.milma.service.responses.items.Category;
import in.cyberprism.libin.milma.service.responses.items.Item;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 30/04/17.
 */

public class ItemResponseMapper implements ResponseMapper<ItemsResponse, HashMap<String, List<Product>>> {
    @Override
    public HashMap<String, List<Product>> getResponse(ItemsResponse item) {
        HashMap<String, List<Product>> map = new HashMap<>();
        for (Category category : item.getCategories()) {
            String categName = category.getCategoryName();
            List<Product> products = new ArrayList<>();

            for (Item item1 : category.getItems()) {
                Product product = new Product();
                product.setCategory(categName);
                product.setName(item1.getItemName());
                product.setItemCode(item1.getItemId());
                product.setImage(item1.getItemImage());
                product.setPrice(item1.getPrice());

                products.add(product);
            }
            map.put(categName, products);
        }
        return map;
    }
}
