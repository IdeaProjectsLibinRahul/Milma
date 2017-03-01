package in.cyberprism.libin.milma.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.events.ItemSelectEvent;
import in.cyberprism.libin.milma.models.Product;

/**
 * Created by libin on 28/02/17.
 */

public class ProductRecyclerAdaper extends RecyclerView.Adapter<ProductRecyclerAdaper.ItemViewHolder> {

    private String key;
    private List<Product> products;
    private Context mContext;

    public ProductRecyclerAdaper(String key, List<Product> products) {
        this.key = key;
        this.products = products;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_child_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.textView.setText(product.getName());
        InputStream ims = null;
        try {
            ims = mContext.getAssets().open(product.getImage());
            Drawable d = Drawable.createFromStream(ims, null);
            holder.imageView.setImageDrawable(d);
            ims.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (product.isSelected()) {
            holder.viewSelected.setVisibility(View.VISIBLE);
        } else {
            holder.viewSelected.setVisibility(View.GONE);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean selected = product.isSelected();
                product.setSelected(!selected);
                notifyDataSetChanged();

                publishCount();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private void publishCount() {
        int count = 0;
        for (Product product : products) {
            if (product.isSelected()) {
                count++;
            }
        }
        ItemSelectEvent event = new ItemSelectEvent();
        event.setKey(key);
        event.setCount(count);
        EventBus.getDefault().post(event);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private View viewSelected;

        public ItemViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageViewItem);
            textView = (TextView) itemView.findViewById(R.id.textViewItemTitle);
            viewSelected = itemView.findViewById(R.id.viewSelected);
        }
    }
}
