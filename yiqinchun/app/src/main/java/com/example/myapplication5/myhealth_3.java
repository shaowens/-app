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

public class myhealth_3 extends Activity {


    private ImageButton imageButton;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab01_1_3);
        imageButton = findViewById(R.id.imageButton14);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      myhealth_3.this.finish();
            }
        });
        Button button = findViewById(R.id.button01_1_3_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText01_1_3_1);
                EditText editText2 = findViewById(R.id.editText01_1_3_2);
                EditText editText3 = findViewById(R.id.editText01_1_3_3);
                EditText editText4 = findViewById(R.id.editText01_1_3_4);
                TextView textView = findViewById(R.id.textView01_1_3_6);
                double height = Double.parseDouble(editText2.getText().toString());
                double weight = Double.parseDouble(editText3.getText().toString());
                double age = Double.parseDouble(editText4.getText().toString());
                String sex = editText.getText().toString();

                if(sex.equals("男")) {
                    double result = (13.7*weight)+(5.0*height)-(6.8*age)+66;
                    BigDecimal bigDecimal = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
                    result = bigDecimal.doubleValue();

                    textView.setText("您的基础代谢为："+result+"KJ");
                } else {
                    double result = (9.6*weight)+(1.8*height)-(4.7*age)+655;
                    BigDecimal bigDecimal = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
                    result = bigDecimal.doubleValue();

                    textView.setText("您的基础代谢为："+result+"KJ");
                }


            }
        });

    }
}
