package in.cyberprism.libin.milma.service.responses;

import java.util.ArrayList;

import in.cyberprism.libin.milma.service.responses.base.BaseResponse;
import in.cyberprism.libin.milma.service.responses.purchase.PurchaseHistory;

/**
 * Created by libin on 09/05/17.
 */

public class HistoryResponse extends BaseResponse {
    private ArrayList<PurchaseHistory> response;

    public ArrayList<PurchaseHistory> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<PurchaseHistory> response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "HistoryResponse{" +
                "response=" + response +
                '}';
    }
}
