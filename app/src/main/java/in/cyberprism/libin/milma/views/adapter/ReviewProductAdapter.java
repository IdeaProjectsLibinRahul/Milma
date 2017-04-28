package in.cyberprism.libin.milma.views.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 03/03/17.
 */

public class ReviewProductAdapter extends RecyclerView.Adapter<ReviewProductAdapter.ReviewProductViewHolder> {

    private final String TAG = getClass().getName();

    private List<Product> products;
    private Context mContext;

    public ReviewProductAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public ReviewProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_review, parent, false);
        return new ReviewProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewProductViewHolder holder, int position) {
        Product product = products.get(position);
        String itemCode = mContext.getString(R.string.item_code, product.getItemCode());

        float quantity = 0;
        float price = 0;
        String quantityText = product.getQuantity();
        String priceText = product.getPrice();
        if (quantityText != null) {
            try {
                quantity = Float.parseFloat(quantityText);
            } catch (NumberFormatException ex) {
                Log.d(TAG, ex.getMessage());
            }
        }
        if (priceText != null) {
            try {
                price = Float.parseFloat(priceText);
            } catch (NumberFormatException ex) {
                Log.d(TAG, ex.getMessage());
            }
        }

        float priceValue = quantity * price;
        String priceHolderText = String.format("%.2f * %.0f = %.2f", price, quantity, priceValue);

        holder.textViewItemCode.setText(itemCode);
        holder.textViewItemName.setText(product.getName());
        holder.textViewItemCategory.setText(product.getCategory());
        holder.textViewItemPrice.setText(priceHolderText);

        InputStream ims = null;
        try {
            ims = mContext.getAssets().open(product.getImage());
            Drawable d = Drawable.createFromStream(ims, null);
            holder.imageViewIcon.setImageDrawable(d);
            ims.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (position % 2 != 0) {
            holder.layoutContainer.setBackgroundResource(R.color.layoutInterBackground);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ReviewProductViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout layoutContainer;
        private TextView textViewItemCode;
        private TextView textViewItemName;
        private TextView textViewItemCategory;
        private TextView textViewItemPrice;
        private ImageView imageViewIcon;

        public ReviewProductViewHolder(View itemView) {
            super(itemView);

            layoutContainer = (RelativeLayout) itemView;
            textViewItemCode = (TextView) itemView.findViewById(R.id.textViewItemCode);
            textViewItemName = (TextView) itemView.findViewById(R.id.textViewItemTitle);
            textViewItemCategory = (TextView) itemView.findViewById(R.id.textViewItemCategory);
            textViewItemPrice = (TextView) itemView.findViewById(R.id.textViewItemPrice);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
        }
    }
}
