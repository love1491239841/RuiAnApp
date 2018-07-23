package com.example.ruianapp.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.bean.MyInform;

public class MyInformDetailsActivity extends AppCompatActivity {
    private TextView name,theme,addtima,type,content;
    private MyInform myInform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inform_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        name = (TextView) findViewById(R.id.inform_de_name);
        theme= (TextView) findViewById(R.id.inform_de_theme);
        addtima= (TextView) findViewById(R.id.inform_de_type);
        type= (TextView) findViewById(R.id.inform_de_type);
        content= (TextView) findViewById(R.id.inform_de_content);
        myInform = (MyInform) getIntent().getSerializableExtra("myInform");
        name.setText(myInform.getSender());
        theme.setText(myInform.getTitle());
        addtima.setText(myInform.getAddTime());
        type.setText(myInform.getType());
        content.setText(myInform.getContent());
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
