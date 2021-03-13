package com.guhun.locatorapplication07.ui.UserFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.alibaba.fastjson.JSON;
import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.data.MyAppGlobal;
import com.guhun.locatorapplication07.data.model.UserModel;
import com.guhun.locatorapplication07.databinding.FragmentUserBinding;
import com.guhun.locatorapplication07.server.AxiosGH;

public class UserFragment extends Fragment {

    UserModel userModel;
    FragmentUserBinding userBinding;
    MyAppGlobal global;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        global = (MyAppGlobal) getActivity().getApplication();
        if(global.getUserId()==null){
            Toast.makeText(getActivity(),"暂未登录",Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(UserFragment.this).navigateUp();
            return null;
        }
        userBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_user,container,false);
        return userBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userBinding.setEdit(false);

        //初始化数据
        new AxiosGH().get(global.getServerUrl() + "/user/getuserinfo?userId=" + global.getUserId(), new AxiosGH.Callback() {
            @Override
            public void onSuccess(String res) {
                userModel = JSON.parseObject(res,UserModel.class);
                userBinding.setUser(userModel);
                String sex = userBinding.getUser().getEmpSex();
                if (sex!=null)
                {
                    switch (sex){
                        case "男":userBinding.txEmpSex.setSelection(0);break;
                        case "女":userBinding.txEmpSex.setSelection(1);break;
                        default:userBinding.txEmpSex.setSelection(2);break;
                    }
                }else {
                    userBinding.txEmpSex.setSelection(2);
                }
            }
            @Override
            public void onFailed(String err) {
                System.out.println(err);
            }
        });


        /**
        * @Description: 按钮点击事件绑定
        * @Param: [view, savedInstanceState]
        * @return: void
        * @Author: GuHun
        * @Date: 2021/3/11
        */
        userBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userBinding.setEdit(true);
            }
        });
        userBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userBinding.getEdit()){
                    userBinding.getUser().setEmpSex(userBinding.txEmpSex.getSelectedItem().toString());
                    new AxiosGH().get(global.getServerUrl() + "/user/setuserinfo", userBinding.getUser(), new AxiosGH.Callback() {
                        @Override
                        public void onSuccess(String res) {
                            if(res.equals("11")){
                                Toast.makeText(getActivity(), "更新成功", Toast.LENGTH_SHORT).show();
                                userBinding.setEdit(false);
                            }else {
                                Toast.makeText(getActivity(), "更新失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailed(String err) {
                            System.out.println(err);
                            Toast.makeText(getActivity(), "更新失败" + err, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}