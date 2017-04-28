package in.cyberprism.libin.milma.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.facade.MilmaFacade;
import in.cyberprism.libin.milma.facade.MilmaFacadeImpl;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.responses.LoginResponse;
import in.cyberprism.libin.milma.service.responses.base.ServiceError;
import in.cyberprism.libin.milma.views.basecomponents.BaseActivity;

public class LoginActivity extends BaseActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private MilmaFacade milmaFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        setListeners();
    }

    private void initComponents() {
        milmaFacade = new MilmaFacadeImpl();
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    private void setListeners() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                milmaFacade.doLogin(username, password, new ServiceCallback<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onRequestTimout() {
                        showMessage("Error", getString(R.string.timeout_message), Constants.MessageType.ERROR);
                    }

                    @Override
                    public void onRequestFail(ServiceError error) {
                        showMessage("Error", error.getErrorMessage(), Constants.MessageType.ERROR);
                    }
                });

            }
        });
    }
}
