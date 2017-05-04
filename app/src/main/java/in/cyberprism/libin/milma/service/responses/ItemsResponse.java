package in.cyberprism.libin.milma.service.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.cyberprism.libin.milma.service.responses.base.BaseResponse;
import in.cyberprism.libin.milma.service.responses.items.Category;

/**
 * Created by libin on 28/04/17.
 */

public class ItemsResponse extends BaseResponse {
    @SerializedName("response")
    private ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
