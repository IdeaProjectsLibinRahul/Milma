package in.cyberprism.libin.milma.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.models.Product;

/**
 * Created by libin on 27/02/17.
 */

public class ProductParser {

    public static HashMap<String, List<Product>> parse(Context context, String fileName) {
        HashMap<String, List<Product>> products = new HashMap<>();

        try {
            InputStream inputStream = context.getAssets().open(fileName);
            InputStreamReader reader = new InputStreamReader(inputStream);

            BufferedReader br = new BufferedReader(reader);
            br.readLine();
            String line = null;
            String category = null;
            List<Product> categoryProduct = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals("")) {
                    if (category != null) {
                        products.put(category, categoryProduct);
                    }
                    category = data[0];
                    categoryProduct = new ArrayList<>();
                }
                String name = data[1];
                String itemCode = data[2];
                String image = data[3];
                String price = data[4];

                Product product = new Product();
                product.setCategory(category);
                product.setImage(image);
                product.setItemCode(Integer.parseInt(itemCode));
                product.setName(name);
                product.setPrice(price);
                categoryProduct.add(product);
            }
            products.put(category, categoryProduct);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}
