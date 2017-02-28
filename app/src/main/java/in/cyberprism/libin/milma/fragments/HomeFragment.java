package in.cyberprism.libin.milma.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.adapter.ProductsAdapter;
import in.cyberprism.libin.milma.basecomponents.BaseFragment;
import in.cyberprism.libin.milma.models.Product;
import in.cyberprism.libin.milma.utils.ProductParser;

/**
 * Created by libin on 27/02/17.
 */

public class HomeFragment extends BaseFragment {

    HashMap<String, List<Product>> products;
    private ExpandableListView listViewItems;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        getData();
        initComponents();
        setAdapter();
        return view;
    }

    private void initComponents() {
        listViewItems = (ExpandableListView) view.findViewById(R.id.listViewItems);

    }

    private void setAdapter() {
        ProductsAdapter adapter = new ProductsAdapter(products, getActivity());
        listViewItems.setAdapter(adapter);
    }

    private void getData() {
        String fileName = "dealer.csv";
        products = ProductParser.parse(getActivity(), fileName);
    }
}
