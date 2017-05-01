package in.cyberprism.libin.milma.facade;

import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.responses.LoginResponse;
import in.cyberprism.libin.milma.service.responses.OrderResponse;
import in.cyberprism.libin.milma.service.responses.order.OrderItem;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 27/04/17.
 */

public interface MilmaFacade {

    void doLogin(String username, String password, ServiceCallback<LoginResponse> callback);

    void getItems(Constants.GroupBy groupBy, ServiceCallback<HashMap<String, List<Product>>> callback);

    void orderItems(List<OrderItem> items, ServiceCallback<OrderResponse> callback);
}
