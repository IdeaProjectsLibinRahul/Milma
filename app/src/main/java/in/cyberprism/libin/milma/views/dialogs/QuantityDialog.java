package in.cyberprism.libin.milma.views.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.events.DataSetChangeEvent;
import in.cyberprism.libin.milma.events.EditDoneEvent;
import in.cyberprism.libin.milma.views.basecomponents.BaseDialog;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 30/04/17.
 */

public class QuantityDialog extends BaseDialog {

    private View view;
    private ImageView imageView;
    private TextView textViewTitle;
    private EditText editTextQuantity;
    private Button buttonAdd;
    private Product product;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_item, container, false);

        initComponents();
        setListeners();

        return view;
    }

    private void initComponents() {
        imageView = (ImageView) view.findViewById(R.id.imageViewDialog);
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitleDialog);
        editTextQuantity = (EditText) view.findViewById(R.id.editTextQuantityDialog);
        buttonAdd = (Button) view.findViewById(R.id.buttonAddDialog);

        if (product != null) {
            String quantity = product.getQuantity();
            if (quantity != null) {
                editTextQuantity.setText(quantity);
            }
        }
    }

    private void setListeners() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantity = editTextQuantity.getText().toString();
                if (quantity.equals("")) {
                    editTextQuantity.setError(getString(R.string.quantity_message));
                } else {
                    product.setQuantity(quantity);
                    dismiss();
                }
            }
        });
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (product.getQuantity() == null) {
            product.setSelected(false);
            EventBus.getDefault().post(new DataSetChangeEvent());
        }
        EventBus.getDefault().post(new EditDoneEvent());
        super.onDismiss(dialog);
    }
}
