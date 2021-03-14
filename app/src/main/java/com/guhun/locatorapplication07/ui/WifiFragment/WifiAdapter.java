package com.guhun.locatorapplication07.ui.WifiFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.data.model.WifiSignalModel;
import com.guhun.locatorapplication07.databinding.WifiItemBinding;

import java.util.ArrayList;

/**
 * @program: LocatorApplication07
 * @description:WifiAdapter
 * @author: GuHun
 * @create: 2021-03-14 17:04
 **/
public class WifiAdapter extends RecyclerView.Adapter<WifiAdapter.MyHolder> {

    ArrayList<WifiSignalModel> signalModelList;

    public WifiAdapter(ArrayList<WifiSignalModel> signalModelList) {
        this.signalModelList = signalModelList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WifiItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.wifi_item, parent, false);
        MyHolder holder = new MyHolder(binding.getRoot());
        holder.setWifiItemBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.getWifiItemBinding().setSignal(signalModelList.get(position));
    }


    @Override
    public int getItemCount() {
        return signalModelList.size();
    }

    // 自定义Holder
    class MyHolder extends RecyclerView.ViewHolder {
        private WifiItemBinding wifiItemBinding;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }

        public WifiItemBinding getWifiItemBinding() {
            return wifiItemBinding;
        }

        public void setWifiItemBinding(WifiItemBinding wifiItemBinding) {
            this.wifiItemBinding = wifiItemBinding;
        }
    }
}
