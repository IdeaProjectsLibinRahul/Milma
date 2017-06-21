package in.cyberprism.libin.milma.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.facade.MilmaFacade;
import in.cyberprism.libin.milma.facade.MilmaFacadeImpl;
import in.cyberprism.libin.milma.service.handlers.ServiceCallback;
import in.cyberprism.libin.milma.service.responses.HistoryResponse;
import in.cyberprism.libin.milma.service.responses.base.ServiceError;
import in.cyberprism.libin.milma.service.responses.purchase.PurchaseHistory;
import in.cyberprism.libin.milma.views.adapter.HistoryAdapter;
import in.cyberprism.libin.milma.views.basecomponents.BaseFragment;

/**
 * Created by libin on 01/05/17.
 */

public class HistoryFragment extends BaseFragment {

    private static final String TAG = HistoryFragment.class.getName();
    private View view;
    private RecyclerView recyclerViewPurchaseHistory;
    private MilmaFacade facade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);

        initComponents();
        getHistory();
        return view;
    }

    private void initComponents() {
        facade = new MilmaFacadeImpl();
        recyclerViewPurchaseHistory = (RecyclerView) view.findViewById(R.id.recyclerViewPurchaseHistory);
        StickyHeaderLayoutManager layoutManager = new StickyHeaderLayoutManager();
        recyclerViewPurchaseHistory.setLayoutManager(layoutManager);
    }

    private void setAdapter(ArrayList<PurchaseHistory> data) {
        HistoryAdapter adapter = new HistoryAdapter(data);
        recyclerViewPurchaseHistory.setAdapter(adapter);
    }

    private void getHistory() {
        facade.getPurchaseHistory(new ServiceCallback<HistoryResponse>() {
            @Override
            public void onResponse(HistoryResponse response) {
                ArrayList<PurchaseHistory> data = getPurchaseHistories(response);
                setAdapter(data);
            }

            @Override
            public void onRequestTimout() {
                Log.i(TAG, "onRequestTimout: History");
            }

            @Override
            public void onRequestFail(ServiceError error) {
                Log.e(TAG, "onRequestFail: " + error.getErrorMessage());
            }
        });
    }

    @NonNull
    private ArrayList<PurchaseHistory> getPurchaseHistories(HistoryResponse response) {
        ArrayList<PurchaseHistory> approved = response.getResponse().getApproved();
        ArrayList<PurchaseHistory> waitingApproval = response.getResponse().getWaitingapproval();
        ArrayList<PurchaseHistory> delivered = response.getResponse().getDelivered();

        ArrayList<PurchaseHistory> data = new ArrayList<>();
        if (approved != null) {
            for (PurchaseHistory history : approved) {
                history.setApprovalStatus("Approved");
                data.add(history);
            }
        }

        if (waitingApproval != null) {
            for (PurchaseHistory history : waitingApproval) {
                history.setApprovalStatus("Waiting Approval");
                data.add(history);
            }
        }

        if (delivered != null) {
            for (PurchaseHistory history : delivered) {
                history.setApprovalStatus("Delivered");
                data.add(history);
            }
        }
        return data;
    }
}
