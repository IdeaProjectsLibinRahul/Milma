package in.cyberprism.libin.milma.service;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.List;

import in.cyberprism.libin.milma.configurations.Config;
import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.models.User;
import in.cyberprism.libin.milma.network.NetworkRequest;
import in.cyberprism.libin.milma.network.NetworkRequestImpl;
import in.cyberprism.libin.milma.network.constants.ServiceURLs;
import in.cyberprism.libin.milma.network.handlers.NetworkCallback;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.mappers.ItemResponseMapper;
import in.cyberprism.libin.milma.service.requests.ItemRequest;
import in.cyberprism.libin.milma.service.requests.LoginRequest;
import in.cyberprism.libin.milma.service.requests.OrderRequest;
import in.cyberprism.libin.milma.service.responses.ItemsResponse;
import in.cyberprism.libin.milma.service.responses.LoginResponse;
import in.cyberprism.libin.milma.service.responses.OrderResponse;
import in.cyberprism.libin.milma.service.responses.base.ServiceError;
import in.cyberprism.libin.milma.service.responses.order.OrderItem;
import in.cyberprism.libin.milma.views.models.Product;

/**
 * Created by libin on 27/04/17.
 */

public class MilmaServiceImpl implements MilmaService {
    @Override
    public void doLogin(String username, String password, final ServiceCallback<LoginResponse> callback) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        final NetworkRequest<LoginResponse> request = new NetworkRequestImpl<>(loginRequest, ServiceURLs.LOGIN, LoginResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                if (response != null) {
                    callback.onResponse(response);
                } else {
                    ServiceError error = new ServiceError();
                    error.setErrorMessage("Response null");

                    callback.onRequestFail(error);
                }
            }

            @Override
            public void onTimeout() {
                callback.onRequestTimout();
            }

            @Override
            public void onFail(ServiceError error) {
                callback.onRequestFail(error);
            }
        });
    }

    @Override
    public void getItems(Constants.GroupBy groupBy, final ServiceCallback<HashMap<String, List<Product>>> callback) {
        User user = Config.getInstance().getUser();

        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setUserId(user.getUserId());
        itemRequest.setGroupBy(groupBy);


        final NetworkRequest<ItemsResponse> request = new NetworkRequestImpl<>(itemRequest, ServiceURLs.HOME, ItemsResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<ItemsResponse>() {
            @Override
            public void onSuccess(ItemsResponse response) {
                if (response != null) {
                    ItemResponseMapper responseMapper = new ItemResponseMapper();
                    HashMap<String, List<Product>> response1 = responseMapper.getResponse(response);

                    callback.onResponse(response1);
                } else {
                    ServiceError error = new ServiceError();
                    error.setErrorMessage("Response null");

                    callback.onRequestFail(error);
                }
            }

            @Override
            public void onTimeout() {
                callback.onRequestTimout();
            }

            @Override
            public void onFail(ServiceError error) {
                callback.onRequestFail(error);
            }
        });
    }

    @Override
    public void orderItems(List<OrderItem> items, final ServiceCallback<OrderResponse> callback) {
        User user = Config.getInstance().getUser();

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId(user.getUserId());
        orderRequest.setItems(items);


        final NetworkRequest<OrderResponse> request = new NetworkRequestImpl<>(orderRequest, ServiceURLs.ORDER, OrderResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<OrderResponse>() {
            @Override
            public void onSuccess(OrderResponse response) {
                if (response != null) {
                    callback.onResponse(response);
                } else {
                    ServiceError error = new ServiceError();
                    error.setErrorMessage("Response null");

                    callback.onRequestFail(error);
                }
            }

            @Override
            public void onTimeout() {
                callback.onRequestTimout();
            }

            @Override
            public void onFail(ServiceError error) {
                callback.onRequestFail(error);
            }
        });
    }
}
