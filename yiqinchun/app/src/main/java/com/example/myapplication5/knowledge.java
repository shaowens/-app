package com.example.myapplication5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class knowledge extends AppCompatActivity {

    Button btn28;
    Button btn29;
    Button btn30;
    WebView webView;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.tab01_4);
        imageButton = findViewById(R.id.imageButton19);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                knowledge.this.finish();
            }
        });
        btn28= (Button) findViewById(R.id.button28);
        btn29= (Button) findViewById(R.id.button29);
        btn30=(Button) findViewById(R.id.button30);
        webView= (WebView) findViewById(R.id.webview);
        btn28.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript脚本
                webView.loadUrl("http://www.boohee.com/jianfei/ruhejianfei");//装载网址对应的页面
                webView.setWebViewClient(new WebViewClient()//设置WebView中客户端的行为
                {
                    //让WebView对点击网页中的URL后作出响应
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url)
                    {
                        view.loadUrl(url);//根据传入的参数再去加载新的网页
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                });
            }
        });
        btn29.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript脚本
                webView.loadUrl("http://www.boohee.com/jianfei/jianfeishipu");//装载网址对应的页面
                webView.setWebViewClient(new WebViewClient()//设置WebView中客户端的行为
                {
                    //让WebView对点击网页中的URL后作出响应
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url)
                    {
                        view.loadUrl(url);//根据传入的参数再去加载新的网页
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                });
            }
        });
        btn30.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript脚本
                webView.loadUrl("http://www.boohee.com/jianfei/yundong");//装载网址对应的页面
                webView.setWebViewClient(new WebViewClient()//设置WebView中客户端的行为
                {
                    //让WebView对点击网页中的URL后作出响应
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url)
                    {
                        view.loadUrl(url);//根据传入的参数再去加载新的网页
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                });
            }
        });
    }
}
