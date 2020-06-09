package com.example.myapplication5;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class myFragment extends Fragment {
    private View ly4;
    private MyHandler handler1;
    private ImageButton imageButton9;
    private ImageView imageView2;
    private ImageButton imageButton12;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
//            ImageView imageView = (ImageView)ly4.findViewById(R.id.imageView2);
//            imageView.setImageBitmap((Bitmap)msg.obj);
            imageView2 = (ImageView) ly4.findViewById(R.id.imageView2);
            imageView2.setImageBitmap((Bitmap)msg.obj);

        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            // Restore the fragment's state here
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the fragment's state here

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (ly4 == null) {
            Intent intent = getActivity().getIntent();

            final String user = intent.getStringExtra("user");
            final String Szong = intent.getStringExtra("Szong");
            List<user> shopInfos = JSON.parseArray(user, user.class);
            final String name = intent.getStringExtra("name");
            ly4 = inflater.inflate(R.layout.tab04, null);

            handler1 = new MyHandler();
            imageButton9 = (ImageButton) ly4.findViewById(R.id.imageButton9);
            imageView2 = (ImageView) ly4.findViewById(R.id.imageView2);
            imageButton12 = (ImageButton) ly4.findViewById(R.id.imageButton12);

            imageButton9.setImageResource(R.drawable.setting);
            imageView2.setImageResource(R.drawable.touxiang);
            imageButton12.setImageResource(R.drawable.daka);

            textView1 = ly4.findViewById(R.id.textView17);
            textView2 = ly4.findViewById(R.id.textView27);
            textView3 = ly4.findViewById(R.id.textView15);
            textView4 = ly4.findViewById(R.id.textView30);
            textView5 = ly4.findViewById(R.id.textView19);
            textView6 = ly4.findViewById(R.id.textView21);
            textView7 = ly4.findViewById(R.id.textView23);
            if(null == shopInfos || shopInfos.size() ==0 ){
                String id =intent.getStringExtra("id");
                String weight =intent.getStringExtra("weight"+"kg");
                String height =intent.getStringExtra("height"+"cm");
                String sex =intent.getStringExtra("sex");
                String year =intent.getStringExtra("year");
                String school =intent.getStringExtra("school");
                textView1.setText(id);
                textView2.setText(weight);
                textView3.setText(height);
                textView4.setText(""+"秒");
                textView5.setText(year);
                textView6.setText(sex);
                textView7.setText(school);
            }else{
                for (user user1 : shopInfos) {


                    textView1.setText(user1.getVname());
                    textView2.setText(user1.getWeight()+"kg");
                    textView3.setText(user1.getHeight()+"cm");
                    textView4.setText(Szong+"秒");
                    textView5.setText(user1.getYear());
                    textView6.setText(user1.getSex());
                    textView7.setText(user1.getSchool());
                }}

            imageButton9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), setting.class);

                    String password = getActivity().getIntent().getStringExtra("password");
                    intent.putExtra("name", name);
                    intent.putExtra("password", password);
                    intent.putExtra("user", user);
                    intent.putExtra("Szong", Szong);
                    startActivity(intent);
                }
            });
            imageButton12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), calendard.class);
                    startActivity(intent);
                }
            });


            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        //http://10.0.2.2:8080/system/fileService/images/admin.jpg   http://localhost:8080/system/fileService/images/admin.jpg
                        String path = "http://120.79.40.20/system/fileService/images/" + name + ".jpg";//String path = "http://120.79.40.20/system/fileService/images/hua.jpg";
                        //2:把网址封装为一个URL对象
                        URL url = new URL(path);
                        //3:获取客户端和服务器的连接对象，此时还没有建立连接
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //4:初始化连接对象
                        conn.setRequestMethod("GET");
                        //设置连接超时
                        conn.setConnectTimeout(8000);
                        //设置读取超时
                        conn.setReadTimeout(8000);
                        //5:发生请求，与服务器建立连接
                        conn.connect();
                        //如果响应码为200，说明请求成功
                        if (conn.getResponseCode() == 200) {
                            //获取服务器响应头中的流
                            InputStream is = conn.getInputStream();
                            //读取流里的数据，构建成bitmap位图
                            Bitmap bm = BitmapFactory.decodeStream(is);
                            Message msg = new Message();
                            msg.obj = bm;
                            handler1.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();



        }
        return ly4;
    }




}
