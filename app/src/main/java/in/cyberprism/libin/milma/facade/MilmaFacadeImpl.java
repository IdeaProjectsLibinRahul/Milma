package in.cyberprism.libin.milma.facade;

import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.service.MilmaService;
import in.cyberprism.libin.milma.service.MilmaServiceImpl;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.responses.ItemsResponse;
import in.cyberprism.libin.milma.service.responses.LoginResponse;

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
    public void getItems(Constants.GroupBy groupBy, ServiceCallback<ItemsResponse> callback) {
        service.getItems(groupBy, callback);
    }
}
