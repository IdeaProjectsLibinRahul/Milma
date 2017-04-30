package in.cyberprism.libin.milma.service;

import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.responses.LoginResponse;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 27/04/17.
 */

public interface MilmaService {

    void doLogin(String username, String password, ServiceCallback<LoginResponse> callback);

    void getItems(Constants.GroupBy groupBy, ServiceCallback<HashMap<String, List<Product>>> callback);
}
