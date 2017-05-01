package in.cyberprism.libin.milma.views.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.views.basecomponents.BaseDialog;

/**
 * Created by libin on 01/05/17.
 */

public class SortDialog extends BaseDialog {

    private View view;
    private SortCallback callback;
    private LinearLayout layoutCategory;
    private LinearLayout layoutQuantity;

    public void setCallback(SortCallback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_sort, container, false);

        initComponents();
        setListeners();

        return view;
    }

    private void initComponents() {
        layoutCategory = (LinearLayout) view.findViewById(R.id.layoutCategory);
        layoutQuantity = (LinearLayout) view.findViewById(R.id.layoutQuantity);
    }

    private void setListeners() {
        layoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onUpdate(Constants.GroupBy.CATEGORY);
                dismiss();
            }
        });

        layoutQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onUpdate(Constants.GroupBy.QUANTITY);
                dismiss();
            }
        });
    }

    public interface SortCallback {
        void onUpdate(Constants.GroupBy groupBy);
    }
}
