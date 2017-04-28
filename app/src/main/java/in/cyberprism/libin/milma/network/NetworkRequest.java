package in.cyberprism.libin.milma.network;

import android.net.Uri;

import com.google.gson.JsonDeserializer;

import java.util.Map;

import in.cyberprism.libin.milma.network.handlers.NetworkCallback;

/**
 * Created by 10945 on 10/26/2016.
 */

public interface NetworkRequest<T> {
    void request(int method, NetworkCallback<T> callback);

    void request(int method, NetworkCallback<T> callback, JsonDeserializer<T> jsonDeserializer);

    void uploadFile(int method, NetworkCallback<T> callback, final Map<String, String> data, Map<String, Uri> files);
}
