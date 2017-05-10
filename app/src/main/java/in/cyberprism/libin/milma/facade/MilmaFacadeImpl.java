package in.cyberprism.libin.milma.facade;

import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.service.MilmaService;
import in.cyberprism.libin.milma.service.MilmaServiceImpl;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.responses.HistoryResponse;
import in.cyberprism.libin.milma.service.responses.LoginResponse;
import in.cyberprism.libin.milma.service.responses.OrderResponse;
import in.cyberprism.libin.milma.service.responses.order.OrderItem;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 27/04/17.
 */

public class MilmaFacadeImpl implements MilmaFacade {

    private MilmaService service;

    public MilmaFacadeImpl() {
        this.service = new MilmaServiceImpl();
    }

    @Override
    public void doLogin(String username, String password, ServiceCallback<LoginResponse> callback) {
        service.doLogin(username, password, callback);
    }

    @Override
    public void getItems(Constants.GroupBy groupBy, ServiceCallback<HashMap<String, List<Product>>> callback) {
        service.getItems(groupBy, callback);
    }

    @Override
    public void orderItems(List<OrderItem> items, ServiceCallback<OrderResponse> callback) {
        service.orderItems(items, callback);
    }

    @Override
    public void getPurchaseHistory(ServiceCallback<HistoryResponse> callback) {
        service.getPurchaseHistory(callback);
    }
}
