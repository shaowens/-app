package com.example.myapplication5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class ListActivity extends Activity {

    private ListView listview;
    private LineChartView lineChart;
    private List<Chart> list;
    private String[] Message= new String[100];
    String[] date ;
    float [] score;
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);



        //initView();
        CMessagePost();
   /*     list=JSON.parseArray(Arrays.toString(Message), Chart.class);

        date=new String[list.size()];
        score=new float[list.size()];

        for (int i=0;i<list.size();i++){

            date[i]="i";
            score[i]=Float.parseFloat(list.get(i).getWeight());
        }

        getAxisXLables();
        getAxisPoints();
        initLineChart();*/
    }

    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);    //折线图上每个数据点的形状，这里是圆形
        line.setCubic(false);
        line.setFilled(false);
        line.setHasLabels(true);
        line.setHasLines(true);
        line.setHasPoints(true);
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis();
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.parseColor("#D6D6D9"));//设置字体颜色

        axisX.setTextSize(8);//设置字体大小
        axisX.setMaxLabelChars(8);//最多几个X轴坐标
        axisX.setValues(mAxisXValues);
        data.setAxisXBottom(axisX);
        axisX.setHasLines(true);


        Axis axisY = new Axis();
        axisY.setName("");
        axisY.setTextSize(8);
        data.setAxisYLeft(axisY);
        //设置行为属性，缩放、滑动、平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 3);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        //设置X轴数据的显示个数（x轴0-7个数据）
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right= 7;
        lineChart.setCurrentViewport(v);
    }

    private void initView() {

        listview=(ListView) findViewById(R.id.listview);
        lineChart = (LineChartView)findViewById(R.id.line_chart);
    }


    private void getAxisXLables(){
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }

    private void getAxisPoints(){
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }
    }
    public void CMessagePost(){
        //获取用户名的值
        String name = getIntent().getStringExtra("name");
        //获取密码的值

        //判断用户名或密码是否为空
        if(TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(),"用户名为空",Toast.LENGTH_LONG).show();//Toast

        }else{
            //获取网络上的servlet路径
            String path="http://120.79.40.20/testhttp/MChart.Servlet";
            //调用postTask,把获取到的用户名，密码与路径放入方法中
            new ListActivity.postTask().execute(name,path);}//Params：开始异步任务执行时传入的参数类型，对应excute（）中传递的参数
    }

    class postTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            //依次获取用户名，密码与路径
            String name = params[0].toString();
            String path = params[1].toString();
            try {
                //获取网络上get方式提交的整个路径
                URL url = new URL(path);
                //打开网络连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置提交方式
                conn.setRequestMethod("POST");
                //设置网络超时时间
                conn.setConnectTimeout(5000);
                //界面上所有的参数名加上他的值
                String s = "name=" + name;
                //获取请求头
                conn.setRequestProperty("Content-Length", s.length() + "");//键是固定的
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//键和值是固定的
                //设置允许对外输出数据
                conn.setDoOutput(true);
                //把界面上的所有数据写出去
                OutputStream os = conn.getOutputStream();
                os.write(s.getBytes());
                if (conn.getResponseCode() == 200) {
                    //用io流与web后台进行数据交互
                    InputStream is = conn.getInputStream();
                    //字节流转字符流
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    //读出每一行的数据
                    String str = br.readLine();
                    //返回读出的每一行的数据
                    return str;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //获取Android studio与web后台数据交互获得的值
            String s = (String) o;
            System.out.println(s);
            initView();
            list=JSON.parseArray(s,Chart.class);

            date=new String[list.size()];
            score=new float[list.size()];

            for (int i=0;i<list.size();i++){

                date[i]="i";
                score[i]=Float.parseFloat(list.get(i).getWeight());
            }

            getAxisXLables();
            getAxisPoints();
            initLineChart();
        }
    }
}