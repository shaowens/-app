package com.example.myapplication5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class myclasses extends AppCompatActivity {

    Button btn2;
    Button btn;
    Button btn10;
    WebView webView;
    private ImageButton imageButton;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.myclasses);

        imageButton = findViewById(R.id.imageButton16);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  myclasses.this.finish();
            }
        });
        btn10= (Button) findViewById(R.id.button10);
        btn= (Button) findViewById(R.id.button);
        btn2=(Button) findViewById(R.id.button2);
        webView= (WebView) findViewById(R.id.webview);
        btn10.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript脚本
                webView.loadUrl("https://www.iqiyi.com/v_19rs6xmk8k.html?vfm=m_303_qqll");//装载网址对应的页面
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
        btn.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript脚本
                webView.loadUrl("https://www.iqiyi.com/a_19rrha9iv1.html");//装载网址对应的页面
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
        btn2.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript脚本
                webView.loadUrl("https://www.iqiyi.com/w_19s9wr74q9.html");//装载网址对应的页面
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