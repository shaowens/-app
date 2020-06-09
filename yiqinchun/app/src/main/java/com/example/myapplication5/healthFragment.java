package com.example.myapplication5;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class healthFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab01, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        ImageButton button1 = (ImageButton)getActivity().findViewById(R.id.button);
        ImageButton button2 = (ImageButton)getActivity().findViewById(R.id.button2);
        ImageButton button3 = (ImageButton)getActivity().findViewById(R.id.button3);
        ImageButton button4 = (ImageButton)getActivity().findViewById(R.id.button4);
        ImageButton button5 = (ImageButton) getActivity().findViewById(R.id.button5);
        ImageButton button6 = (ImageButton)getActivity().findViewById(R.id.button6);
        button1.setImageResource(R.drawable.ziliao);
        button2.setImageResource(R.drawable.yundong2);
        button3.setImageResource(R.drawable.yangsheng);
        button4.setImageResource(R.drawable.yizhishi);
        button5.setImageResource(R.drawable.yishiwu);
        button6.setImageResource(R.drawable.yundong);
        final String name = intent.getStringExtra("name");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), myhealth.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), exercise.class);
                Intent intent1 = getActivity().getIntent();
                String name = intent1.getStringExtra("name");
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), regimen.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), knowledge.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), foods.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), exercisemaster.class);
                startActivity(intent);
            }
        });

    }
}
