package com.example.myapplication5;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import static com.example.myapplication5.R.*;
import static com.example.myapplication5.R.id.imageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class actFragment extends Fragment {
    private View ly2;
    Button btn25;
    Button btn26;
    Button btn27;
    WebView webView;
    private ImageButton imageButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            ly2 = inflater.inflate(layout.activity,null);
        super.onCreate(savedInstanceState);

        btn25= (Button) ly2.findViewById(R.id.button25);
        btn26= (Button) ly2.findViewById(R.id.button26);
        btn27=(Button) ly2.findViewById(R.id.button27);
        webView= (WebView) ly2.findViewById(R.id.webview);
        btn25.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript脚本
                webView.loadUrl("http://xcbnew.stdu.edu.cn/");//装载网址对应的页面
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
        btn26.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript脚本
                webView.loadUrl("http://tiedao.vatuu.com/index.html");//装载网址对应的页面
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
        btn27.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript脚本
                webView.loadUrl("https://xcb.stdu.edu.cn/2009-05-05-02-27-48.html");//装载网址对应的页面
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


        return ly2;
    }


}
