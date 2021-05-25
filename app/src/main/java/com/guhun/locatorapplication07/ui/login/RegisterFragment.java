package com.guhun.locatorapplication07.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.data.MyAppGlobal;
import com.guhun.locatorapplication07.server.AxiosGH;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView user = view.findViewById(R.id.username2);
        TextView pwd = view.findViewById(R.id.password2);
        TextView repwd = view.findViewById(R.id.password3);
        Button register = view.findViewById(R.id.register);
        Button back = view.findViewById(R.id.back);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textIsEmpty(user.getText().toString(),"用户名不能为空"))return;
                if(textIsEmpty(pwd.getText().toString(),"请输入密码"))return;
                if(textIsEmpty(repwd.getText().toString(),"请确认密码"))return;
                if(!pwd.getText().toString().equals(repwd.getText().toString())){
                    Toast.makeText(getActivity(),"两次密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                MyAppGlobal global = (MyAppGlobal)getActivity().getApplication();
                Map<String,Object> params = new HashMap<String, Object>();
                params.put("userId",user.getText().toString());
                params.put("userPwd",pwd.getText().toString());
                new AxiosGH().post(global.getServerUrl() + "/user/insert", params, new AxiosGH.Callback() {
                    @Override
                    public void onSuccess(String res) {
                        switch (res){
                            case "1":Toast.makeText(getActivity(),"注册成功",Toast.LENGTH_SHORT).show();
                                NavHostFragment.findNavController(RegisterFragment.this).navigate(R.id.action_registerFragment_to_loginFragment);
                            break;
                            case "-1":Toast.makeText(getActivity(),"用户名已存在",Toast.LENGTH_SHORT).show();break;
                        }
                    }
                    @Override
                    public void onFailed(String err) {
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RegisterFragment.this).navigateUp();
            }
        });
    }
    // 判断字符串是否为空及进行提示——————GuHun
    public boolean textIsEmpty(String text, String tip){
        if(text.equals("")||text==null){
            Toast.makeText(getActivity(),tip,Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}