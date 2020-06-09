package com.example.myapplication5;

//import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import android.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;

import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    private LinearLayout mTabHealth;
    private LinearLayout mTabAct;
    private LinearLayout mTabShare;
    private LinearLayout mTabMy;

    private ImageButton mImgHealth;
    private ImageButton mImgAct;
    private ImageButton mImgShare;
    private ImageButton mImgMy;

    private Fragment mTab01 = new healthFragment();
    private Fragment mTab02 = new actFragment();
    private Fragment mTab03 = new shareFragment();
    private Fragment mTab04 = new myFragment();

    private FragmentManager fm;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Intent intent =getIntent();//拿到前一个页面的意图
        initView();
        initFragment();
        initevent();
        selectfragment(0);



    }



    private void initFragment() {

        fm = getFragmentManager();//获取的都是Activity里面的Fragment的管理器
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.id_content, mTab01);
        transaction.add(R.id.id_content, mTab02);
        transaction.add(R.id.id_content, mTab03);
        transaction.add(R.id.id_content, mTab04);
        transaction.commit();

    }

    private void initView() {
        mTabHealth = (LinearLayout) findViewById(R.id.id_tab_health);
        mTabAct = (LinearLayout) findViewById(R.id.id_tab_acts);
        mTabShare = (LinearLayout) findViewById(R.id.id_tab_share);
        mTabMy = (LinearLayout) findViewById(R.id.id_tab_my);

        mImgHealth = (ImageButton) findViewById(R.id.imageButton1);
        mImgAct = (ImageButton) findViewById(R.id.imageButton2);
        mImgShare = (ImageButton) findViewById(R.id.imageButton3);
        mImgMy = (ImageButton) findViewById(R.id.imageButton4);
    }


    private void hidefragment(FragmentTransaction transaction) {
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);
    }

    private void selectfragment(int i) {
        FragmentTransaction transaction = fm.beginTransaction();
        hidefragment(transaction);
        resetimg();
        switch (i) {
            case 0:
                transaction.show(mTab01);
                mImgHealth.setImageResource(R.drawable.health2);
                break;
            case 1:
                transaction.show(mTab02);
                mImgAct.setImageResource(R.drawable.huodong2);
                break;
            case 2:
                transaction.show(mTab03);
                mImgShare.setImageResource(R.drawable.share2);
                break;
            case 3:
                transaction.show(mTab04);
                mImgMy.setImageResource(R.drawable.my2);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        resetimg();
        switch (view.getId()) {
            case R.id.id_tab_health:
                selectfragment(0);
                break;
            case R.id.id_tab_acts:
                selectfragment(1);
                break;
            case R.id.id_tab_share:
                selectfragment(2);
                break;
            case R.id.id_tab_my:
                selectfragment(3);
                break;
            default:
                break;

        }
    }

    private void resetimg() {
        mImgHealth.setImageResource(R.drawable.health1);
        mImgAct.setImageResource(R.drawable.huodong1);
        mImgShare.setImageResource(R.drawable.share1);
        mImgMy.setImageResource(R.drawable.my1);
    }

    private void initevent() {
        mTabHealth.setOnClickListener(this);
        mTabAct.setOnClickListener(this);
        mTabShare.setOnClickListener(this);
        mTabMy.setOnClickListener(this);

    }


}
