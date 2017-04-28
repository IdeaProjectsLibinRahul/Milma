package in.cyberprism.libin.milma.facade;

import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.responses.ItemsResponse;
import in.cyberprism.libin.milma.service.responses.LoginResponse;

/**
 * Created by libin on 27/04/17.
 */

public interface MilmaFacade {

    void doLogin(String username, String password, ServiceCallback<LoginResponse> callback);

    void getItems(Constants.GroupBy groupBy, ServiceCallback<ItemsResponse> callback);
}
