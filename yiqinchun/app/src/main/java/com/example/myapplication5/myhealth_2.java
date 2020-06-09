package com.example.myapplication5;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static android.app.PendingIntent.getActivity;

public class myhealth_2 extends Activity {

    private ImageButton imageButton;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab01_1_2);
        imageButton = findViewById(R.id.imageButton15);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                         myhealth_2.this.finish();
            }
        });
        Button button = findViewById(R.id.button01_1_2_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText01_1_2_1);
                EditText editText2 = findViewById(R.id.editText01_1_2_2);
                TextView textView = findViewById(R.id.textView01_1_2_2);
                double height = Double.parseDouble(editText.getText().toString());
                String sex = editText2.getText().toString();
                double h=height;
                if(sex.equals("男")) {
                    double weight = (h - 80) * 0.7;
                    BigDecimal bigDecimal = new BigDecimal(weight).setScale(2, RoundingMode.HALF_UP);
                    weight = bigDecimal.doubleValue();

                    textView.setText("您的标准体重为："+weight+"KG");
                } else {
                    double weight = (h - 70) * 0.6;
                    BigDecimal bigDecimal = new BigDecimal(weight).setScale(2, RoundingMode.HALF_UP);
                    weight = bigDecimal.doubleValue();

                    textView.setText("您的标准体重为："+weight+"KG");
                }


            }
        });

    }
}
