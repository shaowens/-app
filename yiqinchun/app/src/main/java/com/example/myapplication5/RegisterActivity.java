package com.example.myapplication5;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity {
    private Button buttonCode,buttonLogin,backlogin;
    private EditText editTextPhoneNum,editTextCode,editTextPassWord;
    private String phoneNum,code,password;
    private EventHandler eh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonCode = findViewById(R.id.buttonCode);
        buttonLogin = findViewById(R.id.buttonLogin);
        backlogin = findViewById(R.id.backlogin);
        editTextCode = findViewById(R.id.editTextCode);
        editTextPhoneNum = findViewById(R.id.editTextPhoneNum);
        editTextPassWord = findViewById(R.id.editTextPassWord);
        phoneNum = editTextPhoneNum.getText().toString();//获取手机号码
        password = editTextPassWord.getText().toString();
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE){
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
//获取网络上的servlet路径
                        String path="http://120.79.40.20/testhttp/Register.Servlet";
                        //调用postTask,把获取到的用户名，密码与路径放入方法中
                        new postTask().execute(phoneNum,password,path);//Params：开始异步任务执行时传入的参数类型，对应excute（）中传递的参数
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this,"语音验证发送",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this,"验证码已发送",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        Log.i("test","test");
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                    Throwable throwable = (Throwable) data;
                    throwable.printStackTrace();
                    Log.i("1234",throwable.toString());
                    try {
                        JSONObject obj = new JSONObject(throwable.getMessage());
                        final String des = obj.optString("detail");
                        if (!TextUtils.isEmpty(des)){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this,des,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        //注册一个事件回调监听，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eh);
        buttonCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum = editTextPhoneNum.getText().toString();//获取手机号码
                password = editTextPassWord.getText().toString();
                if(!phoneNum.isEmpty()){
                    if(Utils.checkTel(phoneNum)){ //利用正则表达式获取检验手机号
                        // 获取验证码
                        SMSSDK.getVerificationCode("86", phoneNum);
                    }else{
                        Toast.makeText(getApplicationContext(),"请输入有效的手机号",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"请输入手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                phoneNum = editTextPhoneNum.getText().toString();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = editTextCode.getText().toString();
                if(!code.isEmpty()){
                    //提交验证码
                    SMSSDK.submitVerificationCode("86", phoneNum, code);
                }else{
                    Toast.makeText(getApplicationContext(),"请输入验证码",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    // 使用完EventHandler需注销，否则可能出现内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
    class postTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            //依次获取用户名，密码与路径
            String phoneNum=params[0].toString();
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
                String s="phoneNum="+phoneNum+"&password="+password;
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
            Toast.makeText(RegisterActivity.this, sourceStrArray[0], Toast.LENGTH_SHORT).show();
//            if(sourceStrArray.length>=2){
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                Toast.makeText(LoginActivity.this, sourceStrArray[0], Toast.LENGTH_SHORT).show();
//            }else{
//                Intent intent = new Intent(LoginActivity.this,LoginActivity.class);
//                startActivity(intent);
//                Toast.makeText(LoginActivity.this, sourceStrArray[0], Toast.LENGTH_SHORT).show();
//            }
        }
    }
}