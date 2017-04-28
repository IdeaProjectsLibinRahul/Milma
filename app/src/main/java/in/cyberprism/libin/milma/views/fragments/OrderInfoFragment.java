package in.cyberprism.libin.milma.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.views.basecomponents.BaseFragment;

/**
 * Created by libin on 03/03/17.
 */

public class OrderInfoFragment extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_info_fragment, container, false);
        return view;
    }
}
