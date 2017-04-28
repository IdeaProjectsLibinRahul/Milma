package in.cyberprism.libin.milma.service.handlers;


import in.cyberprism.libin.milma.service.responses.base.ServiceError;

/**
 * Created by 10945 on 10/27/2016.
 */

public interface ServiceCallback<T> {
    void onResponse(T response);

    void onRequestTimout();

    void onRequestFail(ServiceError error);
}
