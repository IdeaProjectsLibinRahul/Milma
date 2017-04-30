package in.cyberprism.libin.milma.views.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 28/02/17.
 */

public class ProductsAdapter extends BaseExpandableListAdapter {

    private HashMap<String, List<Product>> products;
    private List<String> heading;
    private Context mContext;

    public ProductsAdapter(HashMap<String, List<Product>> products, Context mContext) {
        this.products = products;
        this.mContext = mContext;
        this.heading = new ArrayList<>(products.keySet());
    }

    @Override
    public int getGroupCount() {
        return products.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return heading.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        String key = heading.get(i);
        return products.get(key);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertView, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }

        TextView textViewHeading = (TextView) convertView.findViewById(R.id.textViewListHeader);
        textViewHeading.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {

        final String key = (String) getGroup(groupPosition);
        final List<Product> childList = (List<Product>) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child, null);
        }

        RecyclerView recyclerView = (RecyclerView) convertView;
        ProductRecyclerAdapter adaper = new ProductRecyclerAdapter(key, childList);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaper);

        return recyclerView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
