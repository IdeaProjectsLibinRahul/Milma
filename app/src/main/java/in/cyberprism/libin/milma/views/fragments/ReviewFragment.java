package in.cyberprism.libin.milma.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.events.EditDoneEvent;
import in.cyberprism.libin.milma.events.EditItemEvent;
import in.cyberprism.libin.milma.views.adapter.ReviewProductAdapter;
import in.cyberprism.libin.milma.views.basecomponents.BaseFragment;
import in.cyberprism.libin.milma.views.dialogs.QuantityDialog;
import in.cyberprism.libin.milma.views.models.Product;

import static in.cyberprism.libin.milma.views.HomeActivity.QUANTITY_INPUT;

/**
 * Created by libin on 03/03/17.
 */

public class ReviewFragment extends BaseFragment {
    private final String TAG = getClass().getName();
    private View view;
    private RecyclerView recyclerViewReview;
    private Button buttonOrder;
    private List<Product> products;
    private ReviewProductAdapter adapter;
    private TextView textViewTotalPrice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_review, container, false);

        parseBundle();
        initComponents();
        setRecyclerAdapter();
        showTotalPrice();
        setListeners();

        return view;
    }

    private void initComponents() {
        recyclerViewReview = (RecyclerView) view.findViewById(R.id.recyclerViewReview);
        buttonOrder = (Button) view.findViewById(R.id.buttonOrder);
        textViewTotalPrice = (TextView) view.findViewById(R.id.textViewTotal);
    }

    private void parseBundle() {
        Bundle bundle = getArguments();
        products = (List<Product>) bundle.getSerializable(BuyProductFragment.ADDED_PRODUCTS);
    }

    private void setRecyclerAdapter() {
        if (products != null) {
            adapter = new ReviewProductAdapter(products);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerViewReview.setLayoutManager(layoutManager);
            recyclerViewReview.setAdapter(adapter);

            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    showTotalPrice();
                }
            });
        }
    }

    private void setListeners() {
        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                OrderInfoFragment fragment = new OrderInfoFragment();
                changeMainView(fragment);
            }
        });
    }

    private void showTotalPrice() {
        float price = 0;
        if (products != null) {
            for (Product product : products) {
                String quantityText = product.getQuantity();
                String priceText = product.getPrice();

                float productPrice = calculatePrice(quantityText, priceText);
                price += productPrice;
            }
        }
        String priceText = getString(R.string.review_price, price);
        textViewTotalPrice.setText(priceText);
    }

    private float calculatePrice(String quantityText, String priceText) {
        float quantity = 0;
        float price = 0;

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
        return priceValue;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EditItemEvent event) {
        QuantityDialog quantityDialog = new QuantityDialog();
        quantityDialog.setProduct(event.getProduct());
        quantityDialog.show(getChildFragmentManager(), QUANTITY_INPUT);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EditDoneEvent event) {
        adapter.notifyDataSetChanged();
    }
}
