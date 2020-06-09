package com.example.myapplication5;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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

public class message extends Activity {
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private Button button;
    private ImageButton imageButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymessage);
        imageButton = findViewById(R.id.imageButton16);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.this.finish();
            }
        });
        editText1 = findViewById(R.id.editText6);
        editText2 = findViewById(R.id.editText8);
        editText3 = findViewById(R.id.editText7);
        editText4 = findViewById(R.id.editText9);
        editText5 = findViewById(R.id.editText11);
        editText6 = findViewById(R.id.editText10);
        button = findViewById(R.id.button7);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MessagePost(v);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                editText1 = findViewById(R.id.editText6);
                editText2 = findViewById(R.id.editText8);
                button = findViewById(R.id.button7);

                Intent i = new Intent(message.this, MainActivity.class);
                String id= editText1.getText().toString();
                String weight = editText2.getText().toString();
                String height = editText3.getText().toString();
                String year = editText4.getText().toString();
                String sex = editText5.getText().toString();
                String school = editText6.getText().toString();
                i.putExtra("id",id);
                i.putExtra("weight",weight);
                i.putExtra("height",height);
                i.putExtra("year",year);
                i.putExtra("sex",sex);
                i.putExtra("school",school);
                startActivity(i);
            }
        });
    }
    public void MessagePost(View view) throws UnsupportedEncodingException {
        String name = getIntent().getStringExtra("name");
        String password = getIntent().getStringExtra("password");
        String id= editText1.getText().toString();
        String weight = editText2.getText().toString();
        String height = editText3.getText().toString();
        String year = editText4.getText().toString();
        String sex = editText5.getText().toString();
        String school = editText6.getText().toString();


        if (TextUtils.isEmpty(name) ||TextUtils.isEmpty(password) ||TextUtils.isEmpty(id) || TextUtils.isEmpty((weight)) || TextUtils.isEmpty((height)) || TextUtils.isEmpty((year)) || TextUtils.isEmpty((sex)) || TextUtils.isEmpty((school))) {
            Toast.makeText(getApplicationContext(), "请确认值是否为空", Toast.LENGTH_LONG).show();//Toast

        } else {
            name= URLEncoder.encode(name,"UTF-8");
            password= URLEncoder.encode(password,"UTF-8");
            id= URLEncoder.encode(id,"UTF-8");
            weight= URLEncoder.encode(weight,"UTF-8");
            height= URLEncoder.encode(height,"UTF-8");
            year= URLEncoder.encode(year,"UTF-8");
            sex= URLEncoder.encode(sex,"UTF-8");
            school= URLEncoder.encode(school,"UTF-8");
            Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_LONG).show();//Toast
            //获取网络上的servlet路径
            String path = "http://120.79.40.20/testhttp/Message.Servlet";
            //调用postTask,把获取到的用户名，密码与路径放入方法中
            new postTask().execute(name,password,id, weight, height, year, sex, school, path);
        }//Params：开始异步任务执行时传入的参数类型，对应excute（）中传递的参数
    }


    class postTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            //依次获取用户名，密码与路径
            String name = params[0].toString();
            String password = params[1].toString();
            String id = params[2].toString();
            String weight = params[3].toString();
            String height = params[4].toString();
            String year = params[5].toString();
            String sex = params[6].toString();
            String school = params[7].toString();
            String path = params[8].toString();
            try {
                //获取网络上get方式提交的整个路径
                URL url = new URL(path);
                //打开网络连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置提交方式
                conn.setRequestMethod("POST");
                //设置网络超时时间
                conn.setConnectTimeout(5000);
                //界面上所有的参数名加上他的值
                String s = "name=" + name +"&password=" + password +"&id=" + id + "&weight=" + weight + "&height=" + height + "&year=" + year + "&sex=" + sex + "&school=" + school;
                //获取请求头

                conn.setRequestProperty("Content-Length", s.length() + "");//键是固定的
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");//键和值是固定的
                //设置允许对外输出数据
                conn.setDoOutput(true);
                //把界面上的所有数据写出去
                OutputStream os = conn.getOutputStream();
                os.write(s.getBytes("UTF-8"));
                if (conn.getResponseCode() == 200) {
                    //用io流与web后台进行数据交互
                    InputStream is = conn.getInputStream();
                    //字节流转字符流
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    //读出每一行的数据
                    String str = br.readLine();
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

    }

}
