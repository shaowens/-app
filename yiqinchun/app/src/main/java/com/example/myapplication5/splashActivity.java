package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.myapplication5.LoginActivity;
import com.example.myapplication5.SplashView;

public class splashActivity extends AppCompatActivity {
    private SplashView splashView;
    private FrameLayout mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstRun();
        mMainView = new FrameLayout(this);

        splashView = new SplashView(this);
        mMainView.addView(splashView);
        setContentView(mMainView);


    }

    private void startLoaddData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //表示数据加载完毕，进入第二个状态
                splashView.splashDisappear();
                Intent intent = new Intent(splashActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        }, 3000);//延时时间
    }
    private void firstRun() {
        SharedPreferences sharedPreferences = getSharedPreferences("FirstRun",0);
        Boolean first_run = sharedPreferences.getBoolean("First",true);
        if (first_run){
            sharedPreferences.edit().putBoolean("First",false).commit();
            Toast.makeText(this,"欢迎使用益青春",Toast.LENGTH_LONG).show();
            startLoaddData();
        }
        else {
//            Toast.makeText(this,"不是第一次",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(splashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }


}