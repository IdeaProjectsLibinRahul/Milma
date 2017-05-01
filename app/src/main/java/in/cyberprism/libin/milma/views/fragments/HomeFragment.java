package in.cyberprism.libin.milma.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.events.SelectedItemsRequestEvent;
import in.cyberprism.libin.milma.facade.MilmaFacade;
import in.cyberprism.libin.milma.facade.MilmaFacadeImpl;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.responses.base.ServiceError;
import in.cyberprism.libin.milma.utils.ProductParser;
import in.cyberprism.libin.milma.views.adapter.ProductsAdapter;
import in.cyberprism.libin.milma.views.basecomponents.BaseFragment;
import in.cyberprism.libin.milma.views.dialogs.SortDialog;
import in.cyberprism.libin.milma.views.models.Product;

import static in.cyberprism.libin.milma.views.fragments.BuyProductFragment.ADDED_PRODUCTS;

/**
 * Created by libin on 27/02/17.
 */

public class HomeFragment extends BaseFragment implements SortDialog.SortCallback {

    public static final String SELECTED_PRODUCTS = "SELECTED_PRODUCTS";
    public static final String SORT_DIALOG = "SORT_DIALOG";
    private final String TAG = getClass().getName();
    private HashMap<String, List<Product>> products;
    private ExpandableListView listViewItems;
    private View view;
    private ProductsAdapter adapter;
    private CountCallback countCallback;

    private MilmaFacade facade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents();
        requestItems(Constants.GroupBy.CATEGORY);
        if (countCallback != null) {
            countCallback.resetCount();
        }

        setHasOptionsMenu(true);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SelectedItemsRequestEvent event) {
//        showQuantityView();
        showReviewView();
    }

    public void setCountCallback(CountCallback countCallback) {
        this.countCallback = countCallback;
    }

    private void showReviewView() {
        List<Product> selectedProducts = getSelectedProducts();

        Bundle bundle = new Bundle();
        bundle.putSerializable(ADDED_PRODUCTS, (Serializable) selectedProducts);
        ReviewFragment fragment = new ReviewFragment();
        fragment.setArguments(bundle);
        changeMainView(fragment);
    }

    private void showQuantityView() {
        List<Product> selectedProducts = getSelectedProducts();
        BuyProductFragment fragment = new BuyProductFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_PRODUCTS, (Serializable) selectedProducts);
        fragment.setArguments(bundle);
        changeMainView(fragment);
    }

    @NonNull
    private List<Product> getSelectedProducts() {
        List<Product> selectedProducts = new ArrayList<>();
        for (String key : products.keySet()) {
            List<Product> productList = products.get(key);
            for (Product product : productList) {
                if (product.isSelected()) {
                    selectedProducts.add(product);
                }
            }
        }
        return selectedProducts;
    }

    private void initComponents() {
        facade = new MilmaFacadeImpl();
        listViewItems = (ExpandableListView) view.findViewById(R.id.listViewItems);

    }

    private void setAdapter() {
        adapter = new ProductsAdapter(products, getActivity());
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
        setAdapter();
    }

    private void requestItems(Constants.GroupBy groupBy) {
        facade.getItems(groupBy, new ServiceCallback<HashMap<String, List<Product>>>() {
            @Override
            public void onResponse(HashMap<String, List<Product>> response) {
                if (response.size() > 0) {
                    products = response;
                    setAdapter();
                } else {
                    showMessage("Info", "No data", Constants.MessageType.SUCCESS);
                }
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item_mock) {
            getData();
        } else if (itemId == R.id.item_order) {
            SortDialog sortDialog = new SortDialog();
            sortDialog.setCallback(this);
            sortDialog.show(getChildFragmentManager(), SORT_DIALOG);
        } else if (itemId == R.id.item_history) {
            HistoryFragment historyFragment = new HistoryFragment();
            changeMainView(historyFragment);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdate(Constants.GroupBy groupBy) {
        requestItems(groupBy);
    }

    public interface CountCallback {
        void resetCount();
    }
}
