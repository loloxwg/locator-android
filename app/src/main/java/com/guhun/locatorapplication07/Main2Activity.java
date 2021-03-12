package com.guhun.locatorapplication07;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Main2Activity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        // 初始化导航栏
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // 初始化头部
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.loginFragment,R.id.nav_user, R.id.nav_site,R.id.nav_wifi)
                .setDrawerLayout(drawer)
                .build();
        // 初始化导航界面
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // 界面和头部绑定
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        // 导航栏和界面绑定
        NavigationUI.setupWithNavController(navigationView, navController);

        Toast.makeText(getApplicationContext(),"暂未登录",Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}