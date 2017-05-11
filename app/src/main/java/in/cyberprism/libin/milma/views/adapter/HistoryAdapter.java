package in.cyberprism.libin.milma.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.service.responses.purchase.PurchaseHistory;
import in.cyberprism.libin.milma.service.responses.purchase.PurchaseItem;

/**
 * Created by libin on 10/05/17.
 */

public class HistoryAdapter extends SectioningAdapter {

    private ArrayList<PurchaseHistory> data;
    private Context mContext;

    public HistoryAdapter(ArrayList<PurchaseHistory> data) {
        this.data = data;
    }

    @Override
    public int getNumberOfSections() {
        return data.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        ArrayList<PurchaseItem> items = data.get(sectionIndex).getItems();
        if (items != null) {
            return items.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    @Override
    public SectioningAdapter.ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_purchase_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public SectioningAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_purchase_header, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType) {
        ArrayList<PurchaseItem> items = data.get(sectionIndex).getItems();
        PurchaseItem item = items.get(itemIndex);
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.textViewName.setText(item.getItemName());
        itemViewHolder.textViewQuantity.setText(item.getItemQuantity());
        itemViewHolder.textViewPrice.setText(mContext.getString(R.string.format_price, item.getItemPrice()));
        Picasso.with(mContext).load(item.getItemImage()).into(itemViewHolder.imageView);
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerUserType) {
        PurchaseHistory history = data.get(sectionIndex);
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
        headerViewHolder.textViewDate.setText(history.getPurchaseDate());
        headerViewHolder.textViewPrice.setText(mContext.getString(R.string.format_price, history.getTotalAmount()));
    }

    private class ItemViewHolder extends SectioningAdapter.ItemViewHolder {

        private TextView textViewName;
        private TextView textViewQuantity;
        private TextView textViewPrice;
        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewItemTitle);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewItemPrice);
            textViewQuantity = (TextView) itemView.findViewById(R.id.textViewItemQuantity);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewItem);
        }
    }

    private class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {

        private TextView textViewDate;
        private TextView textViewPrice;

        HeaderViewHolder(View itemView) {
            super(itemView);

            textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
        }
    }
}
