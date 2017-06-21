package in.cyberprism.libin.milma.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.events.ItemSelectEvent;
import in.cyberprism.libin.milma.events.SelectedItemsRequestEvent;
import in.cyberprism.libin.milma.views.dialogs.QuantityDialog;
import in.cyberprism.libin.milma.views.fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity implements HomeFragment.CountCallback {

    public static final String QUANTITY_INPUT = "QUANTITY_INPUT";
    private Toolbar toolbar;
    private TextView textViewSelectedCount;
    private ImageView imageViewNext;
    private HashMap<String, Integer> itemCount;
    private View.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setListeners();
        setToolBar();

        HomeFragment fragment = new HomeFragment();
        fragment.setCountCallback(this);
        changeMainView(fragment, Constants.Tag.HOME);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ItemSelectEvent event) {
        itemCount.put(event.getKey(), event.getCount());
        if (textViewSelectedCount != null && imageViewNext != null) {
            int count = 0;
            for (String key : itemCount.keySet()) {
                int itemc = itemCount.get(key);
                count += itemc;
            }
            if (count > 0) {
                textViewSelectedCount.setText(count + "");
//                textViewSelectedCount.setVisibility(View.VISIBLE);
                imageViewNext.setVisibility(View.VISIBLE);
            } else {
                textViewSelectedCount.setVisibility(View.GONE);
                imageViewNext.setVisibility(View.GONE);
            }
        }

        if (event.needDialog()) {
            QuantityDialog quantityDialog = new QuantityDialog();
            quantityDialog.setProduct(event.getProduct());
            quantityDialog.show(getSupportFragmentManager(), QUANTITY_INPUT);
        }
    }

    private void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            textViewSelectedCount = (TextView) toolbar.findViewById(R.id.textViewSelectedCount);
            imageViewNext = (ImageView) toolbar.findViewById(R.id.imageViewNext);

            textViewSelectedCount.setOnClickListener(clickListener);
            imageViewNext.setOnClickListener(clickListener);

            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            itemCount = new HashMap<>();
        }
    }

    private void setListeners() {
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewNext.setVisibility(View.GONE);
                textViewSelectedCount.setVisibility(View.GONE);
                EventBus.getDefault().post(new SelectedItemsRequestEvent());
            }
        };
    }

    private void changeMainView(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, tag).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void resetCount() {
        itemCount = new HashMap<>();
    }

    @Override
    public void onBackPressed() {
        if (HomeFragment.selectedProducts != null) {
            HomeFragment.selectedProducts.clear();
        }
        super.onBackPressed();
    }
}
