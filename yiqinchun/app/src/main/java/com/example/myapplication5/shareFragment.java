package com.example.myapplication5;


import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class shareFragment extends Fragment {
    private View ly3;

    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    ListView nameList;
    ListView timeList;
    private String[] Name = new String[0];
    private String[] Time = new String[0];



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        final String name2 = intent.getStringExtra("name");
        if(ly3 ==null){
            ly3 = inflater.inflate(R.layout.tab03,null);
            nameList = (ListView) ly3.findViewById(R.id.nameList);
            timeList = (ListView) ly3.findViewById(R.id.timeList);
            imageView4= (ImageView) ly3.findViewById(R.id.imageView4);
            imageView5= (ImageView) ly3.findViewById(R.id.imageView5);
            imageView6= (ImageView) ly3.findViewById(R.id.imageView6);
            imageView7= (ImageView) ly3.findViewById(R.id.imageView7);
            imageView8= (ImageView) ly3.findViewById(R.id.imageView8);
            imageView9= (ImageView) ly3.findViewById(R.id.imageView9);
            imageView4.setImageResource(R.drawable.tt);
            imageView5.setImageResource(R.drawable.ttt);
            imageView6.setImageResource(R.drawable.tttttt);
            imageView7.setImageResource(R.drawable.tttttt);
            imageView8.setImageResource(R.drawable.ss);
            imageView9.setImageResource(R.drawable.tt);

        }
        class postTask extends AsyncTask {

            @Override
            protected Object doInBackground(Object[] params) {
                String path=params[0].toString();
                try {
                    //获取网络上get方式提交的整个路径
                    URL url=new URL(path);
                    //打开网络连接
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    //设置提交方式
                    conn.setRequestMethod("POST");
                    //设置网络超时时间
                    conn.setConnectTimeout(5000);
                    conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//键和值是固定的
                    //设置允许对外输出数据
                    conn.setDoOutput(true);
                    //把界面上的所有数据写出去
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
                    Toast.makeText(getActivity(), "无排行", Toast.LENGTH_SHORT).show();
                }
                else{
                List<Zong> shopInfos = JSON.parseArray(s, Zong.class);
                List<String> list11 = new ArrayList<>();
                List<String> list22 = new ArrayList<>();
                for (Zong zong : shopInfos) {

                    System.out.println(zong.getName());
                    System.out.println(zong.getZong());

                    list11.add(new String(zong.getName()));
                    list22.add(new String(zong.getSzong()));

//
                }
                Name=list11.toArray(new String[list11.size()]);
                Time=list22.toArray(new String[list22.size()]);

//                Toast.makeText(SActivity.this, s, Toast.LENGTH_SHORT).show();
                dosome();}

            }
            public void dosome(){
                //传递参数
                nameList.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, Name));
                timeList.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, Time));
                setListViewOnTouchAndScrollListener(nameList,timeList);
            }
        }

        String path="http://120.79.40.20/testhttp/DateShow.Servlet";//这里是本地web的地址，先运行httptest的Servlet再启动，才不会闪退。而http://120.79.40.20/testhttp/    http://10.0.2.2:8080/testhttp/
        //调用postTask,把获取到的数据与路径放入方法中，这里没有放数据。
        new postTask().execute(path);
        return ly3;
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
