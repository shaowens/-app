package com.example.myapplication5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class setting extends Activity {
    private ImageButton imageButton;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab04_1);
        imageButton = findViewById(R.id.imageButton23);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting.this.finish();
            }
        });
        Button button1 = findViewById(R.id.button04_1_1);
        Button button2 = findViewById(R.id.button04_1_2);
        Button button3 = findViewById(R.id.button04_1_3);
        Button button4 = findViewById(R.id.button04_1_4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(setting.this, change.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(setting.this,message.class);
                String name = getIntent().getStringExtra("name");
                String password = getIntent().getStringExtra("password");
                intent.putExtra("name", name);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(setting.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting.this,UploadActivity.class);
                String name = getIntent().getStringExtra("name");
                String user = getIntent().getStringExtra("user");
                String Szong = getIntent().getStringExtra("Szong");
                intent.putExtra("name", name);
                intent.putExtra("user", user);
                intent.putExtra("Szong", Szong);
                startActivity(intent);
            }
        });

    }
}
