package com.example.ruianapp.activity;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.bean.MyPlan;
import com.example.ruianapp.bean.MyTask;

public class MyPlanDetailsActivity extends AppCompatActivity {
    private TextView theme,qsTime,jsTime,ry,type,zt,content;
    private MyPlan myPlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        theme= (TextView) findViewById(R.id.plan_de_theme);
        qsTime= (TextView) findViewById(R.id.plan_de_qsTime);
        jsTime= (TextView) findViewById(R.id.plan_de_jsTime);
        ry= (TextView) findViewById(R.id.plan_de_ry);
        type= (TextView) findViewById(R.id.plan_de_type);
        zt= (TextView) findViewById(R.id.plan_de_zt);
        content= (TextView) findViewById(R.id.plan_de_nr);
        myPlan = (MyPlan) getIntent().getSerializableExtra("myPlan");
        theme.setText(myPlan.getTitle());
        qsTime.setText(myPlan.getTimeFrom());
        jsTime.setText(myPlan.getTimeTo());
        ry.setText(myPlan.getEmployees());
        type.setText(myPlan.getCategory());
        if (myPlan.getStatus()==1){
            zt.setText("未完成");
            zt.setTextColor(Color.RED);
        }else {
            zt.setText("已完成");
        }
        content.setText(myPlan.getContent());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
