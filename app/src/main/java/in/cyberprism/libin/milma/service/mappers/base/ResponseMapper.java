package in.cyberprism.libin.milma.service.mappers.base;

/**
 * Created by libin on 30/04/17.
 */

public interface ResponseMapper<T, V> {
    V getResponse(T item);
}
