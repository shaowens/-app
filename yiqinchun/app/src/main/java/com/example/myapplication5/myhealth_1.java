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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class myhealth_1 extends Activity {

    private ImageButton imageButton;
    private EditText editText4;
    private EditText editText3;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab01_1_1);
        imageButton = findViewById(R.id.imageButton16);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myhealth_1.this.finish();
            }
        });
        Button button = findViewById(R.id.button0_1_1_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText3 = findViewById(R.id.editText3);
                EditText editText4 = findViewById(R.id.editText4);
                TextView textView = findViewById(R.id.textView01_1_1_1);

                double height = Double.parseDouble(editText3.getText().toString());
                double weight = Double.parseDouble(editText4.getText().toString());
                double w=weight;
                double h=height/100;
                double result = w/(h*h);
                BigDecimal bigDecimal = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
                result = bigDecimal.doubleValue();
                if(result>=35){
                    textView.setText("您的BMI指数为："+result+"属于重度肥胖");
                }
                else if(result>=30&&result<35){
                    textView.setText("您的BMI指数为："+result+"属于中度肥胖");
                }
                else if(result>=27&&result<30){
                    textView.setText("您的BMI指数为："+result+"属于轻度肥胖");
                }
                else if(result>=24&&result<27){
                    textView.setText("您的BMI指数为："+result+"体重过重");
                }
                else if(result>=18.5&&result<24){
                    textView.setText("您的BMI指数为："+result+"属于正常范围");
                }
                else {
                    textView.setText("您的BMI指数为："+result+"体重过轻");
                }
                try {
                    ChartPost(v);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void ChartPost(View view) throws UnsupportedEncodingException {
        EditText editText4 = findViewById(R.id.editText4);
        String name = getIntent().getStringExtra("name");
        String weight= editText4.getText().toString();

        if (TextUtils.isEmpty(name) ||TextUtils.isEmpty(weight)) {
            Toast.makeText(getApplicationContext(), "请确认值是否为空", Toast.LENGTH_LONG).show();//Toast

        } else {
            name= URLEncoder.encode(name,"UTF-8");
            weight= URLEncoder.encode(weight,"UTF-8");

            Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_LONG).show();//Toast
            //获取网络上的servlet路径
            String path = "http://120.79.40.20/testhttp/Chart.Servlet";
            //调用postTask,把获取到的用户名，密码与路径放入方法中
            new myhealth_1.postTask().execute(name,weight,path);
        }//Params：开始异步任务执行时传入的参数类型，对应excute（）中传递的参数
    }


    class postTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            //依次获取用户名，密码与路径
            String name = params[0].toString();
            String weight = params[1].toString();
            String path = params[2].toString();
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
                String s = "name=" + name +"&weight=" + weight;
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
