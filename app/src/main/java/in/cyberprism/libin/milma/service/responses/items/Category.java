package in.cyberprism.libin.milma.service.responses.items;

import java.util.ArrayList;

/**
 * Created by libin on 28/04/17.
 */

public class Category {
    private int categoryId;
    private String categoryName;

    private ArrayList<Item> items;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", items=" + items +
                '}';
    }
}
