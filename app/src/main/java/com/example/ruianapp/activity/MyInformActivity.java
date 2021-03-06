package com.example.ruianapp.activity;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.ruianapp.R;
import com.example.ruianapp.Utlis.Constants;
import com.example.ruianapp.Utlis.JsonGet;
import com.example.ruianapp.Utlis.UtlisOkhttp;
import com.example.ruianapp.adapter.MyInformAdapter;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public class MyInformActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyInformAdapter myInformAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inform);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        recyclerView= (RecyclerView) findViewById(R.id.myinform_recycler);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        initData(preferences.getInt("id",0)+"");

    }
    private void initData(final String userId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtlisOkhttp.sendIdStatusOkHttpRequest(userId,Constants.MyInform_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showResponse(responseData);
                            System.out.println("54641658951654"+response);
                        }
                        @Override
                        public void onFailure(Call call,IOException e){
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();

    }
    private void showResponse(final String response) {
        //在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("54641658951654"+response);
                try{
                    myInformAdapter=new MyInformAdapter(JsonGet.myInforms(response),MyInformActivity.this);
                    recyclerView.setAdapter(myInformAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
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
