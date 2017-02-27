package in.cyberprism.libin.milma.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import in.cyberprism.libin.milma.R;
import in.cyberprism.libin.milma.basecomponents.BaseFragment;

/**
 * Created by libin on 27/02/17.
 */

public class HomeFragment extends BaseFragment {

    private ExpandableListView listViewItems;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }
}
