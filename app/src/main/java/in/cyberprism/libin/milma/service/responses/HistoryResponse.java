package in.cyberprism.libin.milma.service.responses;

import in.cyberprism.libin.milma.service.responses.base.BaseResponse;
import in.cyberprism.libin.milma.service.responses.purchase.PurchaseApproval;

/**
 * Created by libin on 09/05/17.
 */

public class HistoryResponse extends BaseResponse {
    private PurchaseApproval response;

    public PurchaseApproval getResponse() {
        return response;
    }

    public void setResponse(PurchaseApproval response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "HistoryResponse{" +
                "response=" + response +
                '}';
    }
}
