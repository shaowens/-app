package com.example.myapplication5;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class change extends AppCompatActivity {
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.tab04_1_1);
        imageButton = findViewById(R.id.imageButton16);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             change.this.finish();
            }
        });
        //获取用户名的id
        editText = (EditText) findViewById(R.id.editText04_1_1_1);
        //获取密码的id
        editText2 = (EditText) findViewById(R.id.editText04_1_1_2);

        editText3 = (EditText) findViewById(R.id.editText04_1_1_3);

    }

    public void ChangePost(View view){
        //获取用户名的值
        String name=editText.getText().toString();
        //获取密码的值
        String password=editText2.getText().toString();
        //获取新密码
        String npassword=editText3.getText().toString();
        //判断用户名或密码是否为空
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty((password))||TextUtils.isEmpty((npassword))){
            Toast.makeText(getApplicationContext(),"用户名或密码为空",Toast.LENGTH_LONG).show();//Toast

        }else{
            //获取网络上的servlet路径
            String path="http://120.79.40.20/testhttp/Change.Servlet";
            //调用postTask,把获取到的用户名，密码与路径放入方法中
            new postTask().execute(name,password,npassword,path);}//Params：开始异步任务执行时传入的参数类型，对应excute（）中传递的参数
    }

    class postTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            //依次获取用户名，密码与路径
            String name=params[0].toString();
            String password=params[1].toString();
            String npassword=params[2].toString();
            String path=params[3].toString();
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
                String s="name="+name+"&password="+password+"&npassword="+npassword;
                //获取请求头
                conn.setRequestProperty("Content-Length",s.length()+"");//键是固定的
                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//键和值是固定的
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
            String[] sourceStrArray = s.split(",");
            //吐司Android studio与web后台数据交互获得的值
//            Toast.makeText(LoginActivity.this, sourceStrArray[0], Toast.LENGTH_SHORT).show();
            if(sourceStrArray.length>0){
                Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_LONG).show();//Toast
                Intent intent = new Intent(change.this, LoginActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"用户名或密码错误",Toast.LENGTH_LONG).show();//Toast
            }
        }
    }
}