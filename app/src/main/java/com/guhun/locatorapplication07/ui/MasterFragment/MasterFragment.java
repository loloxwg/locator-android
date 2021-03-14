package com.guhun.locatorapplication07.ui.MasterFragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.guhun.locatorapplication07.data.MyAppGlobal;
import com.guhun.locatorapplication07.R;

public class MasterFragment extends Fragment {

    private MyAppGlobal global;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        global = (MyAppGlobal) getActivity().getApplication();
        if(global.getRight()!="10"){
            Toast.makeText(getActivity(),"权限不足，无法访问",Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(MasterFragment.this).navigateUp();
            return null;
        }
        return inflater.inflate(R.layout.fragment_master, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}