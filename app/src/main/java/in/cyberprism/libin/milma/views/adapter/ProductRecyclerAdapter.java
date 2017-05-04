package in.cyberprism.libin.milma.views.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.events.DataSetChangeEvent;
import in.cyberprism.libin.milma.events.ItemSelectEvent;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 28/02/17.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ItemViewHolder> {

    private String key;
    private List<Product> products;
    private Context mContext;

    public ProductRecyclerAdapter(String key, List<Product> products) {
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
            Picasso.with(mContext).load(product.getImage()).placeholder(R.mipmap.no_image).into(holder.imageView);
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

                publishCount(product, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private void publishCount(Product selectedProduct, boolean needDialog) {
        int count = 0;
        for (Product product : products) {
            if (product.isSelected()) {
                count++;
            }
        }
        ItemSelectEvent event = new ItemSelectEvent();
        event.setKey(key);
        event.setCount(count);
        event.setProduct(selectedProduct);
        event.setNeedDialog(needDialog);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DataSetChangeEvent event) {
        notifyDataSetChanged();
        publishCount(null, false);
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
