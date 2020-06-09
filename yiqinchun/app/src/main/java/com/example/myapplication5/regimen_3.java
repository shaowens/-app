package com.example.myapplication5;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class regimen_3 extends Activity {
    ImageView imageView1;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab01_3_3);
        imageView1 = (ImageView) findViewById(R.id.imageView2);
        imageView1.setImageResource(R.drawable.jiankangbiao);



    }
}