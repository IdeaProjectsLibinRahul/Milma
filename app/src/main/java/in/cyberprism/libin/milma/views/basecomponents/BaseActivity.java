package in.cyberprism.libin.milma.views.basecomponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.utils.ApplicationContextHolder;
import in.cyberprism.libin.milma.views.dialogs.InfoDialog;

/**
 * Created by libin on 28/04/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String SUCCESS_DIALOG = "SUCCESS";

    @Override
    protected void onResume() {
        super.onResume();

        ApplicationContextHolder contextHolder = ApplicationContextHolder.getInstance();
        contextHolder.setContext(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ApplicationContextHolder contextHolder = ApplicationContextHolder.getInstance();
        contextHolder.setContext(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        ApplicationContextHolder contextHolder = ApplicationContextHolder.getInstance();
        contextHolder.setContext(this);
    }

    protected void showMessage(String title, String message, Constants.MessageType type) {
        InfoDialog infoDialog = InfoDialog.newInstance(title, message, type);
        infoDialog.show(getSupportFragmentManager(), SUCCESS_DIALOG);
    }
}
