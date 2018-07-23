package com.example.ruianapp.activity;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.bean.MyTask;
import com.example.ruianapp.bean.Userinfo;

public class MyTaskDetailsActivity extends AppCompatActivity {
    private TextView name,ksTime,jsTime,rwly,zt,sm;
    private MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        name= (TextView) findViewById(R.id.text_de_name);
        ksTime= (TextView) findViewById(R.id.text_de_qsTime);
        jsTime= (TextView) findViewById(R.id.text_de_jsTime);
        rwly= (TextView) findViewById(R.id.text_de_rwly);
        zt= (TextView) findViewById(R.id.text_de_zt);
        sm= (TextView) findViewById(R.id.text_de_sm);
        myTask = (MyTask) getIntent().getSerializableExtra("myTask");
        name.setText(myTask.getName());
        ksTime.setText(myTask.getTimeFrom());
        jsTime.setText(myTask.getTimeTo());
        rwly.setText(myTask.getComeFrom());
        if (myTask.isStatus()){
            zt.setText("未完成");
            zt.setTextColor(Color.RED);
        }else {
            zt.setText("已完成");
        }
        sm.setText(myTask.getComeFrom());

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
