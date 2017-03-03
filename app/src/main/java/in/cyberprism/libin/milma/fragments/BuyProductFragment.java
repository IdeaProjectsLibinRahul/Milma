package in.cyberprism.libin.milma.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.adapter.SelectedItemsAdapter;
import in.cyberprism.libin.milma.basecomponents.BaseFragment;
import in.cyberprism.libin.milma.models.Product;

/**
 * Created by libin on 01/03/17.
 */

public class BuyProductFragment extends BaseFragment {
    public static final String ADDED_PRODUCTS = "ADDED_PRODUCTS";
    private View view;
    private List<Product> products;
    private RecyclerView recyclerViewSelectedProducts;
    private SelectedItemsAdapter adapter;
    private Button buttonBuyNow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_buy_product, container, false);

        parseBundle();
        initComponents();
        setListeners();
        setRecyclerAdapter();

        return view;
    }

    private void parseBundle() {
        Bundle bundle = getArguments();
        products = (List<Product>) bundle.get(HomeFragment.SELECTED_PRODUCTS);
    }

    private void initComponents() {
        recyclerViewSelectedProducts = (RecyclerView) view.findViewById(R.id.recyclerViewSelectedProducts);
        buttonBuyNow = (Button) view.findViewById(R.id.buttonBuyNow);
    }

    private void setListeners() {
        buttonBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(ADDED_PRODUCTS, (Serializable) products);
                ReviewFragment fragment = new ReviewFragment();
                fragment.setArguments(bundle);
                changeMainView(fragment);
            }
        });
    }

    private void setRecyclerAdapter() {
        if (products != null) {
            adapter = new SelectedItemsAdapter(products);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerViewSelectedProducts.setLayoutManager(layoutManager);
            recyclerViewSelectedProducts.setAdapter(adapter);
        }
    }
}
