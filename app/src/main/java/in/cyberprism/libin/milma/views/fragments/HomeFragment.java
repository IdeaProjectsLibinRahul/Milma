package in.cyberprism.libin.milma.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.events.SelectedItemsRequestEvent;
import in.cyberprism.libin.milma.utils.ProductParser;
import in.cyberprism.libin.milma.views.adapter.ProductsAdapter;
import in.cyberprism.libin.milma.views.basecomponents.BaseFragment;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 27/02/17.
 */

public class HomeFragment extends BaseFragment {

    public static final String SELECTED_PRODUCTS = "SELECTED_PRODUCTS";
    private final String TAG = getClass().getName();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SelectedItemsRequestEvent event) {
        List<Product> selectedProducts = new ArrayList<>();
        for (String key : products.keySet()) {
            List<Product> productList = products.get(key);
            for (Product product : productList) {
                if (product.isSelected()) {
                    selectedProducts.add(product);
                }
            }
        }
        BuyProductFragment fragment = new BuyProductFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_PRODUCTS, (Serializable) selectedProducts);
        fragment.setArguments(bundle);
        changeMainView(fragment);
    }

    private void initComponents() {
        listViewItems = (ExpandableListView) view.findViewById(R.id.listViewItems);

    }

    private void setAdapter() {
        ProductsAdapter adapter = new ProductsAdapter(products, getActivity());
        listViewItems.setAdapter(adapter);
        listViewItems.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup) {
                    listViewItems.collapseGroup(previousGroup);
                }
                previousGroup = groupPosition;
            }
        });
        listViewItems.expandGroup(0);
    }

    private void getData() {
        String fileName = "dealer.csv";
        products = ProductParser.parse(getActivity(), fileName);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
