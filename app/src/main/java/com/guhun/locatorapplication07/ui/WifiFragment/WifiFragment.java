package com.guhun.locatorapplication07.ui.WifiFragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.data.model.WifiViewModel;
import com.guhun.locatorapplication07.databinding.FragmentWifiBinding;
import com.guhun.locatorapplication07.server.WifiManageClass;
import com.guhun.locatorapplication07.server.WifiManagerGH;

public class WifiFragment extends Fragment {

    private WifiViewModel mViewModel;

    private FragmentWifiBinding fragmentWifiBinding;

    private WifiManagerGH wifiManagerGH;

    public static WifiFragment newInstance() {
        return new WifiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentWifiBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_wifi,container,false);
        return fragmentWifiBinding.getRoot();
//        return inflater.inflate(R.layout.fragment_wifi,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WifiViewModel.class);
        // TODO: Use the ViewModel
        wifiManagerGH = new WifiManagerGH(getContext());
    }

}