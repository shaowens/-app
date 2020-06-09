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
public class regimen extends AppCompatActivity{
    Button btn20;
    Button btn11;
    Button btn23;
    WebView webView;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.tab01_3);
        imageButton = findViewById(R.id.imageButton20);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regimen.this.finish();
            }
        });
        btn20 = (Button) findViewById(R.id.button20);
        btn11 = (Button) findViewById(R.id.button11);
        btn23 = (Button) findViewById(R.id.button23);
        webView = (WebView) findViewById(R.id.webview);
        btn20.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(regimen.this, regimen_1.class);
                startActivity(intent);
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(regimen.this, regimen_2.class);
                startActivity(intent);
            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {//点击访问按钮
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(regimen.this, regimen_3.class);
                startActivity(intent);
            }
        });
    }
}
