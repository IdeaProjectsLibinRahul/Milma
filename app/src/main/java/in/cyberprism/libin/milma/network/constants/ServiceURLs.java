package in.cyberprism.libin.milma.network.constants;

/**
 * Created by 10945 on 10/27/2016.
 */

public class ServiceURLs {
    public static final String LOGIN = "/Account/Login";
    public static final String HOME = "/AppData/Request";
    public static final String ORDER = "/AppData/Order";
    public static final String HISTORY = "/AppData/Summary";

    private static final String BASE_URL = "http://122.166.96.206/MilmaMobApp/api";

    public static String build(String subURL) {
        return BASE_URL + subURL;
    }
}
