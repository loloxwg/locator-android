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

import com.alibaba.fastjson.JSONObject;
import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.data.MyAppGlobal;
import com.guhun.locatorapplication07.data.model.SiteModel;
import com.guhun.locatorapplication07.databinding.FragmentSiteBinding;
import com.guhun.locatorapplication07.server.AxiosGH;
import com.guhun.locatorapplication07.server.GetBitMap;

import static android.content.ContentValues.TAG;

public class SiteFragment extends Fragment {

    private FragmentSiteBinding binding;
    private MyAppGlobal global;
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

        new AxiosGH().get(global.getServerUrl() + "/usersite/select?userId="+global.getUserId(), new AxiosGH.Callback() {
            @Override
            public void onSuccess(String res) {
                SiteModel siteModel = JSONObject.parseObject(res,SiteModel.class);
                if(siteModel.getMapUrl()!=null){
                    String imgUrl = global.getImgUrl()+siteModel.getMapUrl();
                    GetBitMap.getHttpBitmap(imgUrl, new GetBitMap.Callback() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            binding.imageView.setImageBitmap(bitmap);
                        }
                    });
                }
                binding.setSiteInfo(siteModel);
            }

            @Override
            public void onFailed(String err) {
                Log.e(TAG, "onFailed: "+err );
            }
        });
    }

}