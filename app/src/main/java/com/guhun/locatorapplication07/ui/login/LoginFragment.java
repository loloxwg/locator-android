package com.guhun.locatorapplication07.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.guhun.locatorapplication07.R;
import com.guhun.locatorapplication07.data.MyAppGlobal;
import com.guhun.locatorapplication07.server.AxiosGH;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment{

    MenuItem itemMaster;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }// 初始化

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText usernameEditText = view.findViewById(R.id.username);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.btn_login);
        final Button register = view.findViewById(R.id.btn_register);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if(textIsEmpty(userId,"用户名不能为空")) return;
                if(textIsEmpty(password,"密码不能为空")) return;

                MyAppGlobal global = (MyAppGlobal) getActivity().getApplication();
                String serverUrl = global.getServerUrl();
                Map<String,Object> params = new HashMap<String, Object>();
                params.put("userId", userId);
                params.put("userPwd", password);
                new AxiosGH().post(serverUrl + "/user/login", params, new AxiosGH.Callback() {
                    @Override
                    public void onSuccess(String res) {
                        TextView headText;
                        switch (res){
                            case "1":Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
                                NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_siteFragment);
                                global.setUserId(userId);
                                 headText = getActivity().findViewById(R.id.headText);
                                headText.setText("欢迎に:\n" + userId);
                                global.setRight("1");
                                break;
                            case "10":Toast.makeText(getActivity(),"身份验证：管理员",Toast.LENGTH_SHORT).show();
                                NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_siteFragment);
                                global.setUserId(userId);
                                headText = getActivity().findViewById(R.id.headText);
                                headText.setText("管理员:\n" + userId);
                                global.setRight("10");
                                break;
                            case "-1":Toast.makeText(getActivity(),"用户名错误",Toast.LENGTH_SHORT).show();break;
                            case "-2":Toast.makeText(getActivity(),"密码错误",Toast.LENGTH_SHORT).show();break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + res);
                        }
                    }
                    @Override
                    public void onFailed(String err) {
                        System.out.println(err);
                    }
                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"111",Toast.LENGTH_SHORT).show();
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