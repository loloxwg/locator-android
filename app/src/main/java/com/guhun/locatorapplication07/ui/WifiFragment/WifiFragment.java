package com.guhun.locatorapplication07.ui.WifiFragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.databinding.FragmentWifiBinding;
import com.guhun.locatorapplication07.server.WifiManagerGH;

public class WifiFragment extends Fragment {

    private FragmentWifiBinding fragmentWifiBinding;

    private WifiManagerGH wifiManagerGH;

    public static WifiFragment newInstance() {
        return new WifiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // DataBinding
        fragmentWifiBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_wifi,container,false);
        return fragmentWifiBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        // 获取wifi信息
        wifiManagerGH = new WifiManagerGH(getContext());
        wifiManagerGH.initSignalList(10,0);
        //
        RecyclerView wifiListView = getActivity().findViewById(R.id.wifiListView);
        wifiListView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        wifiListView.setAdapter(new WifiAdapter(wifiManagerGH.getSignalList()));

    }


}