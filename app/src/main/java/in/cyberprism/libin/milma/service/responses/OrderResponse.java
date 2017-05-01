package in.cyberprism.libin.milma.service.responses;

import in.cyberprism.libin.milma.service.responses.base.BaseResponse;

/**
 * Created by libin on 01/05/17.
 */

public class OrderResponse extends BaseResponse {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
