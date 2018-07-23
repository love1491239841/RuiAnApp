package com.example.ruianapp.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.ruianapp.R;
import com.example.ruianapp.bean.MyLeave;

public class MyLeaveDetailsActivity extends AppCompatActivity {
    private TextView name,qsTime,jsTime,status,sy,jb;
    private MyLeave myLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_leave_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        name= (TextView) findViewById(R.id.leave_de_name);
        qsTime= (TextView) findViewById(R.id.leave_de_qsTime);
        jsTime= (TextView) findViewById(R.id.leave_de_jsTime);
        status= (TextView) findViewById(R.id.leave_de_status);
        sy= (TextView) findViewById(R.id.leave_de_qj);
        jb= (TextView) findViewById(R.id.leave_de_jb);
        myLeave = (MyLeave) getIntent().getSerializableExtra("myLeave");
        name.setText(myLeave.getPersons());
        qsTime.setText(myLeave.getTimeFrom());
        jsTime.setText(myLeave.getTimeTo());
        if (myLeave.getStatus()==0){
            status.setText("未通过");
        }else if (myLeave.getStatus()==1){
            status.setText("通过");
        }else {
            status.setText("审核中");
        }
        sy.setText(myLeave.getReason());
        jb.setText(myLeave.getDelegation());
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
