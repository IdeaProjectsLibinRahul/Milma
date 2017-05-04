package in.cyberprism.libin.milma.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.configurations.Config;
import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.facade.MilmaFacade;
import in.cyberprism.libin.milma.facade.MilmaFacadeImpl;
import in.cyberprism.libin.milma.models.User;
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

                        if (response.getStatus() == Constants.Status.SUCCESS) {
                            User user = new User();
                            user.setName(response.getResponse().getName());
                            user.setType(response.getResponse().getType());
                            user.setUserId(response.getResponse().getUserId());
                            Config.getInstance().setUser(user);

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            showMessage("Error", response.getMessage(), Constants.MessageType.ERROR);
                        }
                    }

                    @Override
                    public void onRequestTimout() {
                        showMessage("Error", getString(R.string.timeout_message), Constants.MessageType.ERROR);
                    }

                    @Override
                    public void onRequestFail(ServiceError error) {
                        String errorMessage = error.getErrorMessage();
                        if (errorMessage == null || errorMessage.equals("")) {
                            errorMessage = getString(R.string.server_error);
                        }
                        showMessage("Error", errorMessage, Constants.MessageType.ERROR);

//                        User user = new User();
//                        user.setName("Libin");
//                        user.setType(Constants.Type.DEALER);
//                        user.setUserId(9);
//                        Config.getInstance().setUser(user);
//                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                        startActivity(intent);
                    }
                });

            }
        });
    }
}
