package in.cyberprism.libin.milma.network.handlers;


import in.cyberprism.libin.milma.service.responses.base.ServiceError;

/**
 * Created by 10945 on 10/27/2016.
 */

public interface NetworkCallback<T> {
    void onSuccess(T response);

    void onTimeout();

    void onFail(ServiceError error);
}
