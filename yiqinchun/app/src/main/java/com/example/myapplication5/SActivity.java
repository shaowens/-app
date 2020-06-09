package com.example.myapplication5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SActivity extends AppCompatActivity {
    int l=0;
    ListView nameList;
    ListView caloryList;
    private String[] childName = new String[0];
    private String[] childReliang = new String[0];

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        nameList = findViewById(R.id.nameList);
        caloryList = findViewById(R.id.caloryList);

        Intent intent = getIntent();
        String name = intent.getStringExtra("key1");
        String name1 = null ;
        try {
            name1 = URLEncoder.encode(name,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String name1 = JSON.toJSONString(name);
//        String name1 = java.util.Base64.getUrlEncoder().encodeToString(name.getBytes());
//        String name1= Base64.encodeToString(name.getBytes(),Base64.DEFAULT);
        String password ="wu";
        String password1 = null;
        try {
            password1 = URLEncoder.encode(password,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String password1= java.util.Base64.getUrlEncoder().encodeToString(password.getBytes());
//        String password1 = JSON.toJSONString(password);
        System.out.println(name);
        String path="http://120.79.40.20/testhttp/Search.Servlet";//这里是本地web的地址，先运行httptest的Servlet再启动，才不会闪退。而http://120.79.40.20/testhttp/

//调用postTask,把获取到的数据与路径放入方法中，这里没有放数据。
        new postTask().execute(name1,password1,path);;

    }
    class postTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            //依次获取用户名，密码与路径
            String name=params[0].toString();
            String password=params[1].toString();
            String path=params[2].toString();

            try {
                //获取网络上get方式提交的整个路径
                URL url=new URL(path);
                //打开网络连接
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();

                //设置提交方式
                conn.setRequestMethod("POST");
                //设置网络超时时间
                conn.setConnectTimeout(5000);
                //界面上所有的参数名加上他的值
                String s="name="+name+"&password="+password;
                //获取请求头
                conn.setRequestProperty("Content-Length",s.length()+"");//键是固定的
                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");//键和值是固定的
                //设置允许对外输出数据
                conn.setDoOutput(true);
                //把界面上的所有数据写出去
                OutputStream os=conn.getOutputStream();
                os.write(s.getBytes());
                if(conn.getResponseCode()==200){
                    //用io流与web后台进行数据交互
                    InputStream is=conn.getInputStream();
                    //字节流转字符流
                    BufferedReader br=new BufferedReader(new InputStreamReader(is));
                    //读出每一行的数据
                    String str=br.readLine();
                    //返回读出的每一行的数据
                    return str;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //获取Android studio与web后台数据交互获得的值
            String s= (String) o;
            if(s ==null||s.length()<=0){
                Toast.makeText(SActivity.this, "找不到此类食物", Toast.LENGTH_SHORT).show();
            }
            else{

                List<Shiwu> shopInfos = JSON.parseArray(s, Shiwu.class);
                l=shopInfos.size();
//                if(shopInfos.size()>0){
//                l=shopInfos.size();
                int i=0;
                List<String> list11 = new ArrayList<>();
                List<String> list22 = new ArrayList<>();
                for (Shiwu shiwu : shopInfos) {


                    System.out.println(shiwu.getClasses());
                    System.out.println(shiwu.getName());
                    System.out.println(shiwu.getCalory());

                    list11.add(new String(shiwu.getName()));
                    list22.add(new String(shiwu.getCalory()));

//
                }
                childName=list11.toArray(new String[list11.size()]);
                childReliang=list22.toArray(new String[list22.size()]);

//                Toast.makeText(SActivity.this, s, Toast.LENGTH_SHORT).show();
                dosome();

            }

        }

    }
    public void dosome(){
        //传递参数
        nameList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, childName));
        caloryList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, childReliang));
        setListViewOnTouchAndScrollListener(nameList,caloryList);
    }
    public void setListViewOnTouchAndScrollListener(final ListView listView1,final ListView listView2){


        //设置listview2列表的scroll监听，用于滑动过程中左右不同步时校正
        listView2.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //如果停止滑动
                if(scrollState == 0 || scrollState == 1){
                    //获得第一个子view
                    View subView = view.getChildAt(0);

                    if(subView !=null){
                        final int top = subView.getTop();
                        final int top1 = listView1.getChildAt(0).getTop();
                        final int position = view.getFirstVisiblePosition();

                        //如果两个首个显示的子view高度不等
                        if(top != top1){
                            listView1.setSelectionFromTop(position, top);
                        }
                    }
                }

            }

            public void onScroll(AbsListView view, final int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                View subView = view.getChildAt(0);
                if(subView != null){
                    final int top = subView.getTop();

//      //如果两个首个显示的子view高度不等
                    int top1 = listView1.getChildAt(0).getTop();
                    if(!(top1 - 7 < top &&top < top1 + 7)){
                        listView1.setSelectionFromTop(firstVisibleItem, top);
                        listView2.setSelectionFromTop(firstVisibleItem, top);
                    }

                }
            }
        });

        //设置listview1列表的scroll监听，用于滑动过程中左右不同步时校正
        listView1.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == 0 || scrollState == 1){
                    //获得第一个子view
                    View subView = view.getChildAt(0);

                    if(subView !=null){
                        final int top = subView.getTop();
                        final int top1 = listView2.getChildAt(0).getTop();
                        final int position = view.getFirstVisiblePosition();

                        //如果两个首个显示的子view高度不等
                        if(top != top1){
                            listView1.setSelectionFromTop(position, top);
                            listView2.setSelectionFromTop(position, top);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, final int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                View subView = view.getChildAt(0);
                if(subView != null){
                    final int top = subView.getTop();
                    listView1.setSelectionFromTop(firstVisibleItem, top);
                    listView2.setSelectionFromTop(firstVisibleItem, top);

                }
            }
        });
    }
}
