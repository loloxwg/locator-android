package com.guhun.locatorapplication07.ui.SiteFragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.data.MyAppGlobal;
import com.guhun.locatorapplication07.data.model.UserSiteModel;
import com.guhun.locatorapplication07.data.model.WifiSignalModel;
import com.guhun.locatorapplication07.databinding.FragmentSiteBinding;
import com.guhun.locatorapplication07.server.AxiosGH;
import com.guhun.locatorapplication07.server.GetBitMap;
import com.guhun.locatorapplication07.server.WifiManagerGH;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class SiteFragment extends Fragment {

    private FragmentSiteBinding binding;
    private MyAppGlobal global;
    private WifiManagerGH wifiManagerGH;
    public static SiteFragment newInstance() {
        return new SiteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_site,container,false);
        global = (MyAppGlobal)getActivity().getApplication();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        // 进行定位
        locator();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locator();
                Toast.makeText(getContext(),"定位信息更新完成",Toast.LENGTH_SHORT).show();
            }
        });

    }
    // 开始定位
    public void locator(){
        JSONArray signals = new JSONArray();
        wifiManagerGH = new WifiManagerGH(getContext());
        wifiManagerGH.initSignalList(4,0);
        for (WifiSignalModel model : wifiManagerGH.getSignalList()){
            JSONObject object = new JSONObject();
            object.put("signalMac",model.getSignalMac());
            object.put("signalPower",model.getSignalPower());
            signals.add(object);
        }
        JSONObject param = new JSONObject();
        param.put("userId",global.getUserId());
        param.put("signalList",signals);
        String strParam = param.toString();
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json; charset=UTF-8");
        header.put("accept","application/json");
        new AxiosGH(header).post(global.getServerUrl() + "/signal/locate",strParam, new AxiosGH.Callback() {
            @Override
            public void onSuccess(String res) {
                JSONObject json = JSONObject.parseObject(res);
                Integer wifiId = json.getInteger("wifi_id");
                float sum = json.getFloat("SUM");
                if (sum > global.ERROR_RATE){
                    Toast.makeText(getContext(),"误差率较高："+sum+"%",Toast.LENGTH_SHORT).show();
                }
                getLocatorInfo();
            }
            @Override
            public void onFailed(String err) {
                getLocatorInfo();
            }
        });
    }
    // 获取定位信息
    public void getLocatorInfo(){
        new AxiosGH().get(global.getServerUrl() + "/usersite/select?userId="+global.getUserId(), new AxiosGH.Callback() {
            @Override
            public void onSuccess(String res) {
                UserSiteModel userSiteModel = JSONObject.parseObject(res,UserSiteModel.class);
                if(userSiteModel.getMapUrl()!=null){
                    String imgUrl = global.getImgUrl()+userSiteModel.getMapUrl();
                    GetBitMap.getHttpBitmap(imgUrl, new GetBitMap.Callback() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            binding.imageView.setImageBitmap(bitmap);
                        }
                    });
                }
                binding.setSiteInfo(userSiteModel);
            }

            @Override
            public void onFailed(String err) {
                Log.e(TAG, "onFailed: "+err );
            }
        });
    }
}