package com.example.myapplication5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class exercisemaster extends Activity {
    private ImageButton imageButton01_6_1;
    private ImageButton imageButton01_6_2;
    private ImageButton imageButton01_6_3;
    private ImageButton imageButton01_6_4;
    private ImageButton imageButton01_6_5;
    private Button button1;
    private ImageButton imageButton;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab01_6);
        imageButton01_6_1 = findViewById(R.id.imageButton01_6_1);
        imageButton01_6_2 = findViewById(R.id.imageButton01_6_2);
        imageButton01_6_3 = findViewById(R.id.imageButton01_6_3);
        imageButton01_6_4 = findViewById(R.id.imageButton01_6_4);
        imageButton01_6_5 = findViewById(R.id.imageButton01_6_5);
        imageButton01_6_1.setImageResource(R.drawable.search);
        imageButton01_6_2.setImageResource(R.drawable.new_sport);
        imageButton01_6_3.setImageResource(R.drawable.dongtai);
        imageButton01_6_4.setImageResource(R.drawable.sports);
        imageButton01_6_5.setImageResource(R.drawable.tuijian_sport);
        button1 = findViewById(R.id.button01_6_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(exercisemaster.this, myclasses.class);
                startActivity(intent);
            }
        });
        imageButton = findViewById(R.id.imageButton22);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  exercisemaster.this.finish();
            }
        });

    }
}
