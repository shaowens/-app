package com.example.firstapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;

public class ChartsActivity extends Activity {
    private LineChartView mChart;
    private Map<String, Integer> table = new TreeMap<>();
    private LineChartData mData;
    private boolean hasAxesNames = true;
    private ListView listview;
    private LineChartView lineChart;
    private List<Chart> list;
    private String[] Message = new String[100];
    String[] date;
    float[] score;
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_view);


        initView();
        List<CostBean> allDate = (List<CostBean>) getIntent().getSerializableExtra("cost_list");
        generateValuses(allDate);
        initLineChart();
    }

    private void initLineChart() {
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
        v.right = 7;
        lineChart.setCurrentViewport(v);
    }

    private void initView() {

        listview = (ListView) findViewById(R.id.listview);
        lineChart = (LineChartView) findViewById(R.id.chart);
    }



    private void generateValuses(List<CostBean> allDate) {
        if (allDate != null) {
            for (int i = 0; i < allDate.size(); i++) {
                CostBean costBean = allDate.get(i);
                String costDate = costBean.costDate;
                int costMoney = Integer.parseInt(costBean.costMoney);
                if (!table.containsKey(costDate)) {
                    table.put(costDate, costMoney);

                } else {
                    int originMoney = table.get(costDate);

                    table.put(costDate, originMoney + costMoney);
                }
            }
        }
        int indexX = 0;
        for(Integer value : table.values()){
            mPointValues.add(new PointValue(indexX, value));
            indexX++;
        }
        int i=0;
        for(String key: table.keySet()){
            mAxisXValues.add(new AxisValue(i).setLabel(key));
            i++;
        }
    }
}