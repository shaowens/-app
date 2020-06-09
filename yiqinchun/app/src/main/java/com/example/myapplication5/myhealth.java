package com.example.myapplication5;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static android.app.PendingIntent.getActivity;

public class myhealth extends Activity {

    private ImageButton imageButton;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab01_1);
        imageButton = findViewById(R.id.imageButton16);
        imageButton.setImageResource(R.drawable.back);
        Button button1 = (Button)myhealth.this.findViewById(R.id.button01_1_1);
        Button button2 = (Button)myhealth.this.findViewById(R.id.button01_1_2);
        Button button3 = (Button)myhealth.this.findViewById(R.id.button01_1_3);
        Button button4 = (Button)myhealth.this.findViewById(R.id.button01_1_4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getIntent().getStringExtra("name");
                Intent intent=new Intent(myhealth.this, myhealth_1.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myhealth.this, myhealth_2.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myhealth.this, myhealth_3.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getIntent().getStringExtra("name");
                Intent intent=new Intent(myhealth.this, ListActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  myhealth.this.finish();
            }
        });

    }
}
