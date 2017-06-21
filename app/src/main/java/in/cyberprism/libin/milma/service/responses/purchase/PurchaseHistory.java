package in.cyberprism.libin.milma.service.responses.purchase;

import java.util.ArrayList;

/**
 * Created by libin on 10/05/17.
 */

public class PurchaseHistory {
    private int purchaseId;
    private String purchaseDate;
    private String totalAmount;
    private String approvalStatus;
    private ArrayList<PurchaseItem> items;

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public ArrayList<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<PurchaseItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PurchaseHistory{" +
                "purchaseId=" + purchaseId +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", items=" + items +
                '}';
    }
}
