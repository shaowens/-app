package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.example.myapplication5.Shiwu;


public class foods extends AppCompatActivity {
    // 定义扩展ExpandableListView 所需要变量
    private ExpandableListView expandablelistview;
    private MyExpandAdapter myExpandAdapter;
    private ImageButton imageButton;

    // 一级名称
    private String[] groupName = new String[]{"谷薯芋、杂豆、主食", "蛋类、肉类及制品", "奶类及制品","蔬果和菌藻","坚果、大豆及制品","饮料","食用油、油脂及制品","调味品","零食、点心、冷饮","其它","菜肴"
    };
    private int[] groupPic = new int[]{R.drawable.one, R.drawable.two,
            R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten,R.drawable.eleven};
    // 二级名称
    private String[][] childName = new String[11][100];
    //二级热量
    private String[][] childReliang = new String[11][100];
    //把二级图片去掉了

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.tab01_5);
        imageButton = findViewById(R.id.imageButton21);
        imageButton.setImageResource(R.drawable.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                          foods.this.finish();
            }
        });
        SearchView searchView = findViewById(R.id.sv);
        //设置该SearchView默认是否自动缩小为图标
        searchView.setIconifiedByDefault(false);
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //单机搜索按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("")) {
                    Toast.makeText(foods.this, "您输入的内容为空",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //Toast//实际应用中应该在该方法内执行实际查询，此处仅使用Toast显示用户输入的查询内容
                    Toast.makeText(foods.this, "你的选择是：" + query,
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(foods.this, SActivity.class);
                    intent.putExtra("key1", query);
                    startActivity(intent);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });

        expandablelistview = findViewById(R.id.elv);

        class postTask extends AsyncTask {

            @Override
            protected Object doInBackground(Object[] params) {
                String path=params[0].toString();
                try {
                    //获取网络上get方式提交的整个路径
                    URL url=new URL(path);
                    //打开网络连接
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    //设置提交方式
                    conn.setRequestMethod("POST");
                    //设置网络超时时间
                    conn.setConnectTimeout(5000);
                    conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//键和值是固定的
                    //设置允许对外输出数据
                    conn.setDoOutput(true);
                    //把界面上的所有数据写出去
                    if(conn.getResponseCode()==200){
                        //用io流与web后台进行数据交互
                        InputStream is=conn.getInputStream();
                        //字节流转字符流
                        BufferedReader br=new BufferedReader(new InputStreamReader(is));
                        //读出每一行的数据
                        String str=br.readLine();
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
                String s= (String) o;
                List<Shiwu> shopInfos = JSON.parseArray(s, Shiwu.class);
                int i=0,j=0,k=0,l=0,m=0,n=0,p=0,q=0,r=0,t=0,u=0;


                for (Shiwu shiwu : shopInfos) {

                    System.out.println(shiwu.getClasses());
                    System.out.println(shiwu.getName());
                    System.out.println(shiwu.getCalory());
                    if(shiwu.getClasses().equals("谷薯芋、杂豆、主食\n" +
                            "  "))
                    {
                        childName[0][i]=shiwu.getName();
                        childReliang[0][i]=shiwu.getCalory();
                        i++;
                    }else if(shiwu.getClasses().equals("蛋类、肉类及制品\n" +
                            "  ")){
                        childName[1][j]=shiwu.getName();
                        childReliang[1][j]=shiwu.getCalory();
                        j++;
                    }else if(shiwu.getClasses().equals("奶类及制品\n" +
                            "  ")){
                        childName[2][k]=shiwu.getName();
                        childReliang[2][k]=shiwu.getCalory();
                        k++;
                    }else if(shiwu.getClasses().equals("蔬果和菌藻\n" +
                            "  ")){
                        childName[3][l]=shiwu.getName();
                        childReliang[3][l]=shiwu.getCalory();
                        l++;
                    }else if(shiwu.getClasses().equals("坚果、大豆及制品\n" +
                            "  ")){
                        childName[4][m]=shiwu.getName();
                        childReliang[4][m]=shiwu.getCalory();
                        m++;
                    }else if(shiwu.getClasses().equals("饮料\n" +
                            "  ")){
                        childName[5][n]=shiwu.getName();
                        childReliang[5][n]=shiwu.getCalory();
                        n++;
                    }else if(shiwu.getClasses().equals("食用油、油脂及制品\n" +
                            "  ")){
                        childName[6][p]=shiwu.getName();
                        childReliang[6][p]=shiwu.getCalory();
                        p++;
                    }else if(shiwu.getClasses().equals("调味品\n" +
                            "  ")){
                        childName[7][q]=shiwu.getName();
                        childReliang[7][q]=shiwu.getCalory();
                        q++;
                    }else if(shiwu.getClasses().equals("零食、点心、冷饮\n" +
                            "  ")){
                        childName[8][r]=shiwu.getName();
                        childReliang[8][r]=shiwu.getCalory();
                        r++;
                    }else if(shiwu.getClasses().equals("其它\n" +
                            "  ")){
                        childName[9][t]=shiwu.getName();
                        childReliang[9][t]=shiwu.getCalory();
                        t++;
                    }else if(shiwu.getClasses().equals("菜肴\n" +
                            "  ")){
                        childName[10][u]=shiwu.getName();
                        childReliang[10][u]=shiwu.getCalory();
                        u++;
                    }
                }
//                Toast.makeText(LoginActivity.this, sourceStrArray[0]+sourceStrArray[1], Toast.LENGTH_SHORT).show();
                dosome();
            }

        }
        String path="http://120.79.40.20/testhttp/Show.Servlet";//这里是本地web的地址，先运行httptest的Servlet再启动，才不会闪退。而http://120.79.40.20/testhttp/http://10.0.2.2:8080/testhttp/
        //调用postTask,把获取到的数据与路径放入方法中，这里没有放数据。
        new postTask().execute(path);
    }
    public void dosome(){
        //传递参数
        myExpandAdapter = new MyExpandAdapter(this, groupName, childName, childReliang, groupPic);
        expandablelistview.setAdapter(myExpandAdapter);
//点击Toast
        expandablelistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(),
                        groupName[groupPosition] + ":" + childName[groupPosition][childPosition] + ":" + childReliang[groupPosition][childPosition],
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
