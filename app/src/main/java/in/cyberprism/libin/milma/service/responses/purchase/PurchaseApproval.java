package in.cyberprism.libin.milma.service.responses.purchase;

import java.util.ArrayList;

/**
 * Created by libin on 21/06/17.
 */

public class PurchaseApproval {
    private ArrayList<PurchaseHistory> waitingapproval;
    private ArrayList<PurchaseHistory> delivered;
    private ArrayList<PurchaseHistory> approved;

    public ArrayList<PurchaseHistory> getWaitingapproval() {
        return waitingapproval;
    }

    public void setWaitingapproval(ArrayList<PurchaseHistory> waitingapproval) {
        this.waitingapproval = waitingapproval;
    }

    public ArrayList<PurchaseHistory> getDelivered() {
        return delivered;
    }

    public void setDelivered(ArrayList<PurchaseHistory> delivered) {
        this.delivered = delivered;
    }

    public ArrayList<PurchaseHistory> getApproved() {
        return approved;
    }

    public void setApproved(ArrayList<PurchaseHistory> approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "PurchaseApproval{" +
                "waitingapproval=" + waitingapproval +
                ", delivered=" + delivered +
                ", approved=" + approved +
                '}';
    }
}
