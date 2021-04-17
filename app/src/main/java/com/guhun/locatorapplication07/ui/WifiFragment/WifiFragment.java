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
import android.widget.Toast;

import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.data.MyAppGlobal;
import com.guhun.locatorapplication07.databinding.FragmentWifiBinding;
import com.guhun.locatorapplication07.server.WifiManagerGH;

public class WifiFragment extends Fragment {

    private FragmentWifiBinding fragmentWifiBinding;

    private WifiManagerGH wifiManagerGH;

    private MyAppGlobal global;
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
        // 创建wifi对象
        wifiManagerGH = new WifiManagerGH(getContext());


        // 格式化刷新时间
        int time = global.REFRESHTIME;
        String timeText = time + "s";
        if(time >= 60){
            int mm = time / 60;
            int ss = time % 60;
            timeText = mm + "m" + ss + "s";
            if(mm >= 60){
                int hh = mm / 60;
                mm = mm % 60;
                timeText = hh + "h" + mm + "m" + ss + "s";
            }
        }
        fragmentWifiBinding.textView11.setText("扫描时间间隔："+timeText);

        // 获取wifi信息
        getWifiInfo();

        // 点击获取指纹信息按钮时刷新扫描
        fragmentWifiBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWifiInfo();
                Toast.makeText(getActivity(),"刷新成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 获取wifi信息
     * 无参无返回值
     * 作者咕魂
     * 2021年4月8日10:31:12
     */
    private void getWifiInfo() {
        // 获取wifi信息
        wifiManagerGH.initSignalList(10,0);
        // wifilist绑定适配器
        RecyclerView wifiListView = getActivity().findViewById(R.id.wifiListView);
        wifiListView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        wifiListView.setAdapter(new WifiAdapter(wifiManagerGH.getSignalList()));
    }
}