package com.guhun.locatorapplication07.ui.SiteFragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.data.model.SiteViewModel;

public class SiteFragment extends Fragment {

    private SiteViewModel mViewModel;

    public static SiteFragment newInstance() {
        return new SiteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_site, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SiteViewModel.class);
        // TODO: Use the ViewModel
    }

}