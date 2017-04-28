package in.cyberprism.libin.milma.views.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.views.basecomponents.BaseDialog;

/**
 * Created by libin on 12/03/17.
 */

public class InfoDialog extends BaseDialog {
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String TYPE = "type";
    private View view;
    private Button buttonOK;
    private TextView textViewTitle;
    private TextView textViewMessage;
    private View viewDivider;

    public static InfoDialog newInstance(String title, String message, Constants.MessageType type) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MESSAGE, message);
        bundle.putSerializable(TYPE, type);

        InfoDialog infoDialog = new InfoDialog();
        infoDialog.setArguments(bundle);

        return infoDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_dialog, container, false);

        initComponents();
        parseBundle();

        return view;
    }

    private void initComponents() {
        buttonOK = (Button) view.findViewById(R.id.button_dialog_ok);
        textViewTitle = (TextView) view.findViewById(R.id.dialogTitle);
        textViewMessage = (TextView) view.findViewById(R.id.dialogMessage);
        viewDivider = (View) view.findViewById(R.id.dialogDivider);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString(TITLE);
            String message = bundle.getString(MESSAGE);
            Constants.MessageType type = (Constants.MessageType) bundle.getSerializable(TYPE);

            textViewTitle.setText(title);
            textViewMessage.setText(message);

            if (type == Constants.MessageType.ERROR) {
                viewDivider.setBackgroundColor(getResources().getColor(R.color.message_error));
            } else if (type == Constants.MessageType.SUCCESS) {
                viewDivider.setBackgroundColor(getResources().getColor(R.color.message_success));
            } else {
                viewDivider.setBackgroundColor(getResources().getColor(R.color.message_default));
            }
        }
    }
}
