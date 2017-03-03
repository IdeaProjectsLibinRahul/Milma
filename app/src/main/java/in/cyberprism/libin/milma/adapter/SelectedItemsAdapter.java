package in.cyberprism.libin.milma.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.models.Product;

/**
 * Created by libin on 01/03/17.
 */

public class SelectedItemsAdapter extends RecyclerView.Adapter<SelectedItemsAdapter.SelectedItemsViewHolder> {
    private List<Product> products;
    private Context mContext;

    public SelectedItemsAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public SelectedItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.selected_list_item, parent, false);
        return new SelectedItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectedItemsViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.selectedItemTitle.setText(product.getName());
        String price = mContext.getString(R.string.selected_price, product.getPrice());
        holder.selectedItemPrice.setText(price);

        InputStream ims = null;
        try {
            ims = mContext.getAssets().open(product.getImage());
            Drawable d = Drawable.createFromStream(ims, null);
            holder.selectedItemImage.setImageDrawable(d);
            ims.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.selectedItemQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String quantity = editable.toString();
                product.setQuantity(quantity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class SelectedItemsViewHolder extends RecyclerView.ViewHolder {

        private TextView selectedItemTitle;
        private ImageView selectedItemImage;
        private TextView selectedItemPrice;
        private EditText selectedItemQuantity;

        public SelectedItemsViewHolder(View itemView) {
            super(itemView);

            selectedItemTitle = (TextView) itemView.findViewById(R.id.selectedItemTitle);
            selectedItemImage = (ImageView) itemView.findViewById(R.id.selectedItemImage);
            selectedItemPrice = (TextView) itemView.findViewById(R.id.selectedItemPrice);
            selectedItemQuantity = (EditText) itemView.findViewById(R.id.selectedItemQuantity);
        }
    }
}
