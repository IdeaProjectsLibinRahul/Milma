package in.cyberprism.libin.milma.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.events.EditDoneEvent;
import in.cyberprism.libin.milma.events.EditItemEvent;
import in.cyberprism.libin.milma.facade.MilmaFacade;
import in.cyberprism.libin.milma.facade.MilmaFacadeImpl;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.responses.OrderResponse;
import in.cyberprism.libin.milma.service.responses.base.ServiceError;
import in.cyberprism.libin.milma.service.responses.order.OrderItem;
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
    private MilmaFacade facade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_review, container, false);

        setHasOptionsMenu(true);

        parseBundle();
        initComponents();
        setRecyclerAdapter();
        showTotalPrice();
        setListeners();

        return view;
    }

    private void initComponents() {
        facade = new MilmaFacadeImpl();
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

                List<OrderItem> items = new ArrayList<OrderItem>();
                for (Product product : products) {
                    OrderItem item = new OrderItem();
                    item.setItemId(product.getItemCode());
                    item.setQuantity(Integer.parseInt(product.getQuantity()));

                    items.add(item);
                }

                facade.orderItems(items, new ServiceCallback<OrderResponse>() {
                    @Override
                    public void onResponse(OrderResponse response) {
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        for (int i = 1; i < fm.getBackStackEntryCount(); ++i) {
                            fm.popBackStack();
                        }

                        OrderInfoFragment fragment = new OrderInfoFragment();
                        changeMainView(fragment, Constants.Tag.ORDER);
                    }

                    @Override
                    public void onRequestTimout() {
                        showMessage("Error", getString(R.string.timeout_message), Constants.MessageType.TIME_OUT);
                    }

                    @Override
                    public void onRequestFail(ServiceError error) {
                        String errorMessage = error.getErrorMessage();
                        if (errorMessage == null || errorMessage.equals("")) {
                            errorMessage = getString(R.string.server_error);
                        }
                        showMessage("Error", errorMessage, Constants.MessageType.ERROR);
                    }
                });

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.review, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item_add) {
            addNewItems();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewItems() {
        HomeFragment homeFragment = new HomeFragment();
        changeMainView(homeFragment);
    }
}
