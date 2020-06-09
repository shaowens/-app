package com.example.myapplication5;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class exercise extends Activity {
    private Chronometer timer;
    private Timer timer1;
    private ImageButton imageButton01_2_1;
    private ImageButton imageButton01_2_2;
    private TextView textView;
    private Button button;
    private EditText editText,editText2;
    private DatePicker date1;
    private ImageButton imageButton;

    private long recordingTime = 0;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab01_2);
        Intent intent =getIntent();
        String name = intent.getStringExtra("name");

        imageButton = findViewById(R.id.imageButton10);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.this.finish();
            }
        });
        imageButton01_2_1 = findViewById(R.id.imageButton01_2_1);
        imageButton01_2_2 = findViewById(R.id.imageButton01_2_2);
        imageButton01_2_1.setImageResource(R.drawable.stop);
        imageButton01_2_2.setImageResource(R.drawable.next);
        timer = (Chronometer) findViewById(R.id.timer);
        timer1 = new Timer();
        textView = (TextView) findViewById(R.id.textView01_2_2);
        editText = findViewById(R.id.editText01_2_1);
        editText2 = findViewById(R.id.editText01_2_2);
        editText2.setText(name);
        date1 = (DatePicker) findViewById(R.id.date);

    }

    public void startClick(View view) {
        timer.setBase(SystemClock.elapsedRealtime() - recordingTime);
        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
        timer.setFormat("0" + String.valueOf(hour) + ":%s");
        timer.start();

    }

    public void stopClick(View view) {
        timer.stop();
        recordingTime = SystemClock.elapsedRealtime()- timer.getBase();
    }

    public void clearClick(View view) {
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
    }


    public void DataPost(View view) throws UnsupportedEncodingException {
        String name =editText2.getText().toString();

        name= URLEncoder.encode(name,"UTF-8");
        String type = editText.getText().toString();
        type= URLEncoder.encode(type,"UTF-8");
        String time = timer.getText().toString();

        String date = date1.getYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDayOfMonth();
        if (TextUtils.isEmpty(type) || TextUtils.isEmpty((time)) || TextUtils.isEmpty((date))||TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "请确认值是否为空", Toast.LENGTH_LONG).show();//Toast

        } else {
            Toast.makeText(getApplicationContext(), "打卡成功", Toast.LENGTH_LONG).show();//Toast

            //获取网络上的servlet路径
            String path = "http://120.79.40.20/testhttp/Date.Servlet";//http://10.0.2.2:8080/testhttp/
            //调用postTask,把获取到的用户名，密码与路径放入方法中
            new postTask().execute(type, time, date, name, path);
        }//Params：开始异步任务执行时传入的参数类型，对应excute（）中传递的参数
    }


    class postTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            //依次获取用户名，密码与路径
            String type = params[0].toString();
            String time = params[1].toString();
            String date = params[2].toString();
            String name = params[3].toString();
            String path = params[4].toString();
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
                String s = "type=" + type + "&time=" + time + "&date=" + date + "&name=" +name;
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
